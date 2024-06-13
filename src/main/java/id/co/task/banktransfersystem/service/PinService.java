package id.co.task.banktransfersystem.service;

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
 * @created on 6/11/2024
 */
@Slf4j
@Service
public class PinService {
    @Autowired
    private WebClient genericWebClient;

    public GetPinRs getPin(String accountNo) {
        GetPinRs getPinRs = new GetPinRs().setStatus("error");
        String uri = "/users/1234567890/pin";
        try {
            ResponseEntity<GetPinRs> getPinRsResponseEntity = this.genericWebClient.get()
                    .uri(uri).retrieve()
                    .toEntity(GetPinRs.class)
                    .block(Duration.ofSeconds(10));

            log.info("[GET HTTP RESPONSE - SUCCESS][{}][{}][{}]", uri, getPinRsResponseEntity.getStatusCode(), getPinRsResponseEntity.getBody());
            getPinRs = getPinRsResponseEntity.getBody();
        } catch (WebClientResponseException ex) {
            log.info("[GET HTTP RESPONSE - FAILED][{}][{}][{}]", uri, ex.getStatusCode(), ex.getResponseBodyAsString());
        }

        return getPinRs;
    }
}
