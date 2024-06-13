package id.co.task.banktransfersystem.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mika Silaen
 * @created on 6/12/2024
 */
@Data
@Accessors(chain = true)
public class BankTransferRs {
    private String transactionId;
    private String message;
}
