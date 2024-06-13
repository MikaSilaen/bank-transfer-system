package id.co.task.banktransfersystem.repository.impl;

import id.co.task.banktransfersystem.model.entity.BankDetails;
import id.co.task.banktransfersystem.model.entity.Users;
import id.co.task.banktransfersystem.repository.BankDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Mika Silaen
 * @created on 6/12/2024
 */

@Repository
public class BankDetailRepositoryImpl implements BankDetailRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${query.transaction.getBankDetail}")
    private String queryGetBankDetail;

    @Override
    public BankDetails getBankDetails(String accountNo) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account_number", accountNo);
        return namedParameterJdbcTemplate.queryForObject(queryGetBankDetail, params, BeanPropertyRowMapper.newInstance(BankDetails.class));
    }
}
