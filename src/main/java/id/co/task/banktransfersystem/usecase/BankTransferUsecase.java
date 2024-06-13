package id.co.task.banktransfersystem.usecase;

import id.co.task.banktransfersystem.exception.InsufficientFundException;
import id.co.task.banktransfersystem.exception.UnauthorizedException;
import id.co.task.banktransfersystem.exception.UserNotFoundException;
import id.co.task.banktransfersystem.model.KeyValueMap;
import id.co.task.banktransfersystem.model.TransactionType;
import id.co.task.banktransfersystem.model.entity.*;
import id.co.task.banktransfersystem.model.request.BankTransferRq;
import id.co.task.banktransfersystem.model.response.BankTransferRs;
import id.co.task.banktransfersystem.model.response.ExternalBankTransferRs;
import id.co.task.banktransfersystem.model.response.GenericResponse;
import id.co.task.banktransfersystem.model.response.GetPinRs;
import id.co.task.banktransfersystem.repository.AccountRepository;
import id.co.task.banktransfersystem.repository.BankDetailRepository;
import id.co.task.banktransfersystem.repository.TransactionRepository;
import id.co.task.banktransfersystem.repository.UserRepository;
import id.co.task.banktransfersystem.service.NotificationService;
import id.co.task.banktransfersystem.service.PaymentGatewayService;
import id.co.task.banktransfersystem.service.PinService;
import id.co.task.banktransfersystem.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Slf4j
@Component
public class BankTransferUsecase {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    BankDetailRepository bankDetailRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PaymentGatewayService paymentGatewayService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    private PinService pinService;

    @Value("${bank.code.internal.bank}")
    private String prefixInternalBank;

    @Value("${bank.code.mapping}")
    private String bankCodeMapping;


    public ResponseEntity<Object> bankTransfer(BankTransferRq bankTransferRq) throws Exception {
        String sourceAccount = bankTransferRq.getAccountNoSender();
        String destinationAccount = bankTransferRq.getAccountNoDestination();
        BigDecimal amount = bankTransferRq.getAmount();

        GenericResponse<BankTransferRs> detailGenericResponse = new GenericResponse<BankTransferRs>();
        /*validate user*/
        try {
            Users user = userRepository.getUserDetail(bankTransferRq.getUsername());
            if (user == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND);
                throw new UserNotFoundException(bankTransferRq.getUsername());
            }
            /*validate password*/
            if (!user.getPassword().equalsIgnoreCase(bankTransferRq.getPassword())) {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED);
                throw new Exception("Password is not match");
            }
            /*validate bank account*/
            BankDetails bankDetails = bankDetailRepository.getBankDetails(bankTransferRq.getAccountNoDestination());
            if (bankDetails == null) {
                throw new UserNotFoundException(destinationAccount);
            }
            if (prefixInternalBank.equalsIgnoreCase(bankDetails.getBankCode())) {
                //Internal Bank Transfer
                /*validate PIN*/
                GetPinRs getPinRs = pinService.getPin(bankTransferRq.getPin());
                if (!bankTransferRq.getPin().equalsIgnoreCase(getPinRs.getData())) {
                    throw new UnauthorizedException("Invalid PIN");
                }
                String transactionId = doInternalBankTransfer(bankTransferRq);

                /*insert transaction*/
                Transactions transaction = new Transactions();
                transaction.setAmount(amount);
                transaction.setTransactionType(String.valueOf(TransactionType.CASH_TRANSFER));
                transaction.setTransactionDate(new Date());
                transaction.setTransactionId(transactionId);
                transaction.setAccountNo(sourceAccount);
                transaction.setAccountNoDestination(destinationAccount);
                transaction.setStatus("Success");
                transactionRepository.insertTransactionDetails(transaction);
                BankTransferRs bankTransferRs = new BankTransferRs();
                bankTransferRs.setTransactionId(transactionId);
                bankTransferRs.setMessage(String.format("Bank Transfer to account %s was successful", destinationAccount));

                detailGenericResponse = detailGenericResponse
                        .setCode("00")
                        .setMessage("success")
                        .setStatus("ok");
                detailGenericResponse.setData(bankTransferRs);
                ResponseEntity.status(HttpStatus.OK);
            } else {
                //External Bank Transfer
                /*validate PIN*/
                GetPinRs getPinRs = pinService.getPin(bankTransferRq.getPin());
                if (!bankTransferRq.getPin().equalsIgnoreCase(getPinRs.getData())) {
                    throw new UnauthorizedException("Invalid PIN");
                }
                BankTransferRs bankTransferRs = doExternalBankTransfer(bankTransferRq, bankDetails);
                detailGenericResponse = detailGenericResponse
                        .setCode("00")
                        .setMessage("success")
                        .setStatus("ok");
                detailGenericResponse.setData(bankTransferRs);
            }
        } catch (Exception e) {
            detailGenericResponse = detailGenericResponse
                    .setCode("01")
                    .setMessage(e.getMessage())
                    .setStatus("error");
            detailGenericResponse.setData(null);
            return new ResponseEntity<>(detailGenericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(detailGenericResponse, HttpStatus.OK);
    }


    private String doInternalBankTransfer(BankTransferRq bankTransferRq) throws InsufficientFundException {
        String sourceAccount = bankTransferRq.getAccountNoSender();
        String destinationAccount = bankTransferRq.getAccountNoDestination();
        BigDecimal amount = bankTransferRq.getAmount();
        /*get account detail destination*/
        Accounts accountDetailDestination = accountRepository.getAccountDestination(destinationAccount);

        /*check available fund source account*/
        Accounts accountDetail = accountRepository.getAccountDetail(sourceAccount, bankTransferRq.getUsername());
        BigDecimal sourceBalance = accountDetail.getBalance();
        if (sourceBalance.compareTo(amount) < 0) {
            throw new InsufficientFundException("Insufficient fund.");
        }
        //update account source balance (debit)
        BigDecimal newSourceBalance = sourceBalance.subtract(amount);
        accountDetail.setBalance(newSourceBalance);
        accountRepository.updateAccountBalance(sourceAccount, newSourceBalance);

        //update account destination balance (credit)
        BigDecimal newDestinationBalance = accountDetailDestination.getBalance().add(amount);
        accountDetailDestination.setBalance(newDestinationBalance);
        accountRepository.updateAccountBalance(destinationAccount, newDestinationBalance);

        String transactionId = UUID.randomUUID().toString();
        /*sent notif */
        notificationService.sendNotif(bankTransferRq);

        return transactionId;
    }

    private BankTransferRs doExternalBankTransfer(BankTransferRq bankTransferRq, BankDetails bankDetails) throws InsufficientFundException {
        String sourceAccount = bankTransferRq.getAccountNoSender();
        String destinationAccount = bankTransferRq.getAccountNoDestination();
        BigDecimal amount = bankTransferRq.getAmount();

        /*check available fund source account*/
        Accounts accountDetail = accountRepository.getAccountDetail(sourceAccount, bankTransferRq.getUsername());
        BigDecimal sourceBalance = accountDetail.getBalance();
        if (sourceBalance.compareTo(amount) < 0) {
            throw new InsufficientFundException("Insufficient fund.");
        }
        //update account source balance (withdraw)
        BigDecimal newSourceBalance = sourceBalance.subtract(amount);
        accountDetail.setBalance(newSourceBalance);
        accountRepository.updateAccountBalance(sourceAccount, newSourceBalance);

        String prefixExternalBank = bankDetails.getBankCode();
        //Get mapping bank code, then call api gateway to call external APIs or send queue to topic using solace event broker
        //String bankDetail = getBankMapping().getAsString(prefixExternalBank);
        BankTransferRs bankTransferRs = new BankTransferRs();
        ExternalBankTransferRs transferRs = paymentGatewayService.transferToExternalBank(bankTransferRq);
        if (transferRs != null && transferRs.getStatus().equalsIgnoreCase("SUCCESS")){
            /*insert transaction*/
            Transactions transaction = new Transactions();
            transaction.setAmount(amount);
            transaction.setTransactionType(String.valueOf(TransactionType.CASH_TRANSFER));
            transaction.setTransactionDate(new Date());
            transaction.setTransactionId(transferRs.getExternalId());
            transaction.setAccountNo(sourceAccount);
            transaction.setAccountNoDestination(destinationAccount);
            transaction.setStatus("Success");
            transactionRepository.insertTransactionDetails(transaction);

            bankTransferRs.setTransactionId(transferRs.getExternalId());
            bankTransferRs.setMessage(String.format("Bank Transfer to account %s was successful", destinationAccount));
        }
        /*sent notif */
        notificationService.sendNotif(bankTransferRq);
       return bankTransferRs;
    }

    public KeyValueMap getBankMapping() {
        return GsonUtils.getInstance().fromJson(this.bankCodeMapping, KeyValueMap.class);
    }

}

