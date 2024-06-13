package id.co.task.banktransfersystem.service;

import brave.Tracer;
import id.co.task.banktransfersystem.model.request.BankTransferRq;
import id.co.task.banktransfersystem.model.response.NotificationRs;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mika Silaen
 * @created on 6/13/2024
 */
@SpringBootTest
@Slf4j
class NotificationServiceTest {

    @MockBean
    Tracer tracer;

    @Autowired
    NotificationService notificationService;

    @Test
    void sendNotif(){
        BankTransferRq bankTransferRq = new BankTransferRq()
                .setAmount(BigDecimal.valueOf(1000))
                .setAccountNoDestination("735732345")
                .setAccountNoSender("735743925")
                .setPin("1234")
                .setUsername("hellow");

        NotificationRs notificationRs = notificationService.sendNotif(bankTransferRq);
        Assertions.assertNotNull(notificationRs);
    }
}