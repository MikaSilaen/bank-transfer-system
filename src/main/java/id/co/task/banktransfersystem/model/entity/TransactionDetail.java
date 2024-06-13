package id.co.task.banktransfersystem.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Data
@Accessors(chain = true)
public class TransactionDetail {
    private String month;
    private String year;
    private String amount;
    private String count;

}
