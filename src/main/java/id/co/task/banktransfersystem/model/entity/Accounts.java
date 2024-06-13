package id.co.task.banktransfersystem.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */

@Data
@Accessors(chain = true)
public class Accounts {
    private String accountNo;
    private int userId;
    private String accountType;
    private BigDecimal balance;
    private Date openDate;
}
