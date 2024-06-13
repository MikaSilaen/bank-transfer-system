package id.co.task.banktransfersystem.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Mika Silaen
 * @created on 6/13/2024
 */

@Data
@Accessors(chain = true)
public class ExternalBankTransferRs {
    @JsonProperty(value = "external_id")
    private String externalId;
    @JsonProperty(value = "amount")
    private BigDecimal amount;
    @JsonProperty(value = "account_sender")
    private String accountSender;
    @JsonProperty(value = "account_receiver")
    private String accountReceiver;
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "transfer_type")
    private String transferType;
}
