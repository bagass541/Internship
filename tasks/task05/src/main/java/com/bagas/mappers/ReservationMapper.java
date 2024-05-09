package com.bagas.mappers;

import com.bagas.entities.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.bagas.util.ParameterChecker.checkParameter;

public class ReservationMapper {

    public static Reservation createReservation(Long id, Long userId, Long computerId,
                                                LocalDate date, LocalTime time) {
        return Reservation.builder()
                .id(checkParameter(id))
                .userId(checkParameter(userId))
                .computerId(checkParameter(computerId))
                .date(checkParameter(date))
                .time(checkParameter(time))
                .build();
    }
}
