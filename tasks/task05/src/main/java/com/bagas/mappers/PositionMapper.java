package com.bagas.mappers;

import com.bagas.entities.Position;

import java.math.BigDecimal;

import static com.bagas.util.ParameterChecker.checkParameter;

public class PositionMapper {

    public static Position createPosition(Long id, String name, BigDecimal salary) {
        return Position.builder()
                .id(checkParameter(id))
                .name(checkParameter(name))
                .salary(checkParameter(salary))
                .build();
    }
}
