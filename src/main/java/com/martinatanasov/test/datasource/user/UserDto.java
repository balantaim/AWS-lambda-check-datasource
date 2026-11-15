package com.martinatanasov.test.datasource.user;


import java.sql.Timestamp;


public record UserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        Timestamp creationDate,
        Timestamp modifyDate
) {
}





