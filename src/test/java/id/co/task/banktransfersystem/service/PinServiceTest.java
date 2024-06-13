package id.co.task.banktransfersystem.service;

import brave.Tracer;
import id.co.task.banktransfersystem.model.request.BankTransferRq;
import id.co.task.banktransfersystem.model.response.GetPinRs;
import id.co.task.banktransfersystem.model.response.NotificationRs;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
class PinServiceTest {
    @MockBean
    Tracer tracer;

    @Autowired
    PinService pinService;

    @Test
    void getPin(){
        GetPinRs getPinRs = pinService.getPin("1234567890");
        Assertions.assertEquals(getPinRs.getData(), "1234");
    }
}