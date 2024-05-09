package com.bagas.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Position {

    private long id;

    private String name;

    private BigDecimal salary;
}
