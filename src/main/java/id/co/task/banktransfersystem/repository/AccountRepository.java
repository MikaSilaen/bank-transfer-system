package id.co.task.banktransfersystem.repository;

import id.co.task.banktransfersystem.model.entity.Accounts;

import java.math.BigDecimal;

/**
 * @author Mika Silaen
 * @created on 6/12/2024
 */
public interface AccountRepository {
    Accounts getAccountDetail(String accountNo, String username);
    Accounts getAccountDestination(String accountNo);
    int updateAccountBalance(String accountNo, BigDecimal newBalance);
}
