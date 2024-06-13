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
public class Transactions
{
    private String transactionId;
    private String accountNo;
    private String transactionType;
    private BigDecimal amount;
    private String status;
    private Date transactionDate;
    private String accountNoDestination;
}
