package com.iprofile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int checkUserExist(String username) {
        return jdbcTemplate
                .queryForObject("select count(*) from USERS where username = ?",
                        new Object[]{username}, Integer.class);
    }
}
