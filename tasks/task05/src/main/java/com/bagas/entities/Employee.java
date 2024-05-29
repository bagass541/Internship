package com.bagas.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Employee {

    private long id;

    private String name;

    private String secondName;

    private LocalDate birthday;

    private long positionId;

    private String phoneNumber;

}
