package id.co.task.banktransfersystem.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */

@Data
@Accessors(chain = true)
public class BankDetails {
    private int id;
    private String accountNo;
    private String bankCode;
    private String bankName;
    private String accountHolderName;
    private String accountHolderAddres;
}
