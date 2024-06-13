package id.co.task.banktransfersystem.delivery;


import id.co.task.banktransfersystem.model.request.BankTransferRq;
import id.co.task.banktransfersystem.usecase.BankTransferUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */

@RestController
public class TransactionController {
    @Autowired
    private BankTransferUsecase transactionUsecase;

    @PostMapping("/api/transaction/transfer")
    public ResponseEntity<?> bankTransfer(@RequestBody() BankTransferRq bodyRq) throws Exception {
        return transactionUsecase.bankTransfer(bodyRq);
    }
}
