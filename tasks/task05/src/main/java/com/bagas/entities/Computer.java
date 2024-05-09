package com.bagas.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Computer {

    private long id;

    private TypeComputer typeComputer;

    private long locationId;
}
