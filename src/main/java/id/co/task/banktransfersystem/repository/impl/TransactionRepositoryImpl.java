package id.co.task.banktransfersystem.repository.impl;

import id.co.task.banktransfersystem.model.entity.TransactionDetail;
import id.co.task.banktransfersystem.model.entity.Transactions;
import id.co.task.banktransfersystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${query.transaction.insertTransaction}")
    private String queryInsertTransaction;

    @Override
    public void insertTransactionDetails(Transactions transactions) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("transaction_id", transactions.getTransactionId());
        params.addValue("account_number", transactions.getAccountNo());
        params.addValue("transaction_type", transactions.getTransactionType());
        params.addValue("amount", transactions.getAmount());
        params.addValue("transaction_date", transactions.getTransactionDate());
        params.addValue("status", transactions.getStatus());
        params.addValue("account_number_destination", transactions.getAccountNoDestination());
        namedParameterJdbcTemplate.update(queryInsertTransaction, params);
    }
}
