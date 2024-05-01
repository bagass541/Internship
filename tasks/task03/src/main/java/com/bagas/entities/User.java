package com.bagas.entities;

import java.time.LocalDate;

import com.bagas.entities.enums.SexType;
import com.bagas.exceptions.IncorrectField;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    @Setter
    private long id;

    private String name;

    private String secondName;

    @Setter
    private SexType sex;

    @Setter
    private LocalDate birthday;

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

    public void setName(String name) throws IncorrectField {
        checkCorrectness(name);
        this.name = name;
    }

    public void setSecondName(String secondName) {
        checkCorrectness(secondName);
        this.secondName = secondName;
    }
}
