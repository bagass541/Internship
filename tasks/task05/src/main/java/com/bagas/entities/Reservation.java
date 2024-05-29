package com.bagas.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Reservation {

    private long id;

    private long userId;

    private long computerId;

    private LocalDate date;

    private LocalTime time;
}
