package com.epam.mapper;

import com.epam.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            User user = User
                    .builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .balance(resultSet.getBigDecimal("balance"))
                    .status(resultSet.getString("status"))
                    .build();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
