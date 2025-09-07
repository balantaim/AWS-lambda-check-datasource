package com.martinatanasov.test.datasource.user;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String sql = "SELECT id, email, first_name, last_name, creation_date, modify_date FROM users";

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all users from the database via JDBC driver
    public List<UserDTO> findAllUsers() {
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new UserDTO(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("modify_date")
                )
        );
    }

}
