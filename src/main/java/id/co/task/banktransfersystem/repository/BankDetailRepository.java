package id.co.task.banktransfersystem.repository;

import id.co.task.banktransfersystem.model.entity.BankDetails;

/**
 * @author Mika Silaen
 * @created on 6/12/2024
 */

public interface BankDetailRepository {
    BankDetails getBankDetails(String accountNo);
}
