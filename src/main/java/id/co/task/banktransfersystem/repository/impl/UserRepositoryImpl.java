package id.co.task.banktransfersystem.repository.impl;

import id.co.task.banktransfersystem.model.entity.TransactionDetail;
import id.co.task.banktransfersystem.model.entity.Users;
import id.co.task.banktransfersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${query.transaction.getUserDetail}")
    private String queryGetUserDetail;


    @Override
    public Users getUserDetail(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        return namedParameterJdbcTemplate.queryForObject(queryGetUserDetail, params, BeanPropertyRowMapper.newInstance(Users.class));
    }
}
