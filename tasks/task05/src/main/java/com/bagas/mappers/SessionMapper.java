package com.bagas.mappers;

import com.bagas.entities.Session;

import java.time.LocalTime;

import static com.bagas.util.ParameterChecker.checkParameter;

public class SessionMapper {

    public static Session createSession(Long id, Long userId, Long employeeId,
                                        Long gameId, Long computerId, LocalTime duration) {
        return Session.builder()
                .id(checkParameter(id))
                .userId(checkParameter(userId))
                .employeeId(checkParameter(employeeId))
                .gameId(checkParameter(gameId))
                .computerId(checkParameter(computerId))
                .duration(checkParameter(duration))
                .build();
    }
}
