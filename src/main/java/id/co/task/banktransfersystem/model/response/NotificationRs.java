package id.co.task.banktransfersystem.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mika Silaen
 * @created on 6/13/2024
 */
@Data
@Accessors(chain = true)
public class NotificationRs {
    private String code;
    private String status;
    private String message;
}
