package com.bagas.entities;

import java.time.LocalDate;

import com.bagas.entities.enums.SexType;
import com.bagas.exceptions.IncorrectField;
import lombok.Getter;

@Getter
public class User {

    private final long id;

    private final String name;

    private final String secondName;

    private final SexType sex;

    private final LocalDate birthday;

    public User(long id, String name, String secondName, SexType sex, LocalDate birthday) throws IncorrectField {
        this.id = id;

        checkCorrectness(name);
        checkCorrectness(secondName);

        this.name = name;
        this.secondName = secondName;
        this.sex = sex;
        this.birthday = birthday;
    }

    private void checkCorrectness(String name) {
        if (name.split("\\w+").length > 1) {
            throw new IncorrectField("Имя/Фамилия состоит из 2 слов и более");
        }
    }
}
