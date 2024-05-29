package com.bagas.mappers;

import com.bagas.entities.User;

import java.time.LocalDate;

import static com.bagas.util.ParameterChecker.checkParameter;

public class UserMapper {

    public static User createUser(Long id, String name, String secondName, LocalDate birthday, String phoneNumber) {
        return User.builder()
                .id(checkParameter(id))
                .name(checkParameter(name))
                .secondName(checkParameter(secondName))
                .birthday(checkParameter(birthday))
                .phoneNumber(checkParameter(phoneNumber))
                .build();
    }
}
