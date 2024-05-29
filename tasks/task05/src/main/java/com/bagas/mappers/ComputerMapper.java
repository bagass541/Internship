package com.bagas.mappers;

import com.bagas.entities.Computer;
import com.bagas.entities.TypeComputer;

import static com.bagas.util.ParameterChecker.checkParameter;

public class ComputerMapper {

    public static Computer createComputer(Long id, TypeComputer typeComputer, Long locationId) {
        return Computer.builder()
                .id(checkParameter(id))
                .typeComputer(checkParameter(typeComputer))
                .locationId(checkParameter(locationId))
                .build();
    }
}
