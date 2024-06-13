package id.co.task.banktransfersystem.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Data
@Accessors(chain = true)
public class GetPinRs {
    private String status;
    private String code;
    private String message;
    private String data;
}
