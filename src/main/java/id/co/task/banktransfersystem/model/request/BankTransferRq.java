package id.co.task.banktransfersystem.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Data
@Accessors(chain = true)
public class BankTransferRq {
    private String username;
    private String password;
    private String accountNoSender;
    private String pin;
    private String accountNoDestination;
    private BigDecimal amount;
}
