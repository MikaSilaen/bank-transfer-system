package id.co.task.banktransfersystem.service;

import id.co.task.banktransfersystem.model.request.BankTransferRq;
import id.co.task.banktransfersystem.model.response.ExternalBankTransferRs;
import id.co.task.banktransfersystem.model.response.GetPinRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

/**
 * @author Mika Silaen
 * @created on 6/13/2024
 */
@Slf4j
@Service
public class PaymentGatewayService {

    @Autowired
    private WebClient genericWebClient;

    public ExternalBankTransferRs transferToExternalBank(BankTransferRq bankTransferRq) {
        ExternalBankTransferRs transferRs = new ExternalBankTransferRs();
        String uri = "/create-external-transfer";
        try {
            ResponseEntity<ExternalBankTransferRs> transferResponseEntity = this.genericWebClient.post()
                    .uri(uri).retrieve()
                    .toEntity(ExternalBankTransferRs.class)
                    .block(Duration.ofSeconds(10));

            log.info("[GET HTTP RESPONSE - SUCCESS][{}][{}][{}]", uri, transferResponseEntity.getStatusCode(), transferResponseEntity.getBody());
            transferRs = transferResponseEntity.getBody();
        } catch (WebClientResponseException ex) {
            log.info("[GET HTTP RESPONSE - FAILED][{}][{}][{}]", uri, ex.getStatusCode(), ex.getResponseBodyAsString());
        }

        return transferRs;
    }
}
