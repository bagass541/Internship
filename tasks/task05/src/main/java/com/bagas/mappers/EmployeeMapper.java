package com.bagas.mappers;

import com.bagas.entities.Employee;

import java.time.LocalDate;

import static com.bagas.util.ParameterChecker.checkParameter;

public class EmployeeMapper {

    public static Employee createEmployee(Long id, String name, String secondName, LocalDate birthday,
                                          Long positionId, String phoneNumber) {
        return Employee.builder()
                .id(checkParameter(id))
                .name(checkParameter(name))
                .secondName(checkParameter(secondName))
                .birthday(checkParameter(birthday))
                .positionId(checkParameter(positionId))
                .phoneNumber(checkParameter(phoneNumber))
                .build();
    }
}
