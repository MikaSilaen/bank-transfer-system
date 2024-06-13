package id.co.task.banktransfersystem.repository.impl;

import id.co.task.banktransfersystem.model.entity.Accounts;
import id.co.task.banktransfersystem.model.entity.BankDetails;
import id.co.task.banktransfersystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author Mika Silaen
 * @created on 6/12/2024
 */

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${query.transaction.getAccountDetail}")
    private String querygetAccountDetail;
    @Value("${query.transaction.getAccountDestination}")
    private String querygetAccountDestination;
    @Value("${query.transaction.updateAccountBalance}")
    private String queryUpdateAccountBalance;

    @Override
    public Accounts getAccountDetail(String accountNo, String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account_number", accountNo);
        params.addValue("username", username);
        return namedParameterJdbcTemplate.queryForObject(querygetAccountDetail, params, BeanPropertyRowMapper.newInstance(Accounts.class));

    }

    @Override
    public Accounts getAccountDestination(String accountNo) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account_number", accountNo);
        return namedParameterJdbcTemplate.queryForObject(querygetAccountDestination, params, BeanPropertyRowMapper.newInstance(Accounts.class));
    }

    @Override
    public int updateAccountBalance(String accountNo, BigDecimal newBalance) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account_number", accountNo);
        params.addValue("balance", newBalance);
        return namedParameterJdbcTemplate.update(queryUpdateAccountBalance, params);
    }
}
