package com.bagas.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Session {

    private long id;

    private long userId;

    private long employeeId;

    private long gameId;

    private long computerId;

    private LocalTime duration;
}
