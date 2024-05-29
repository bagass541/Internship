package com.bagas.mappers;

import com.bagas.entities.Game;

import static com.bagas.util.ParameterChecker.checkParameter;

public class GameMapper {

    public static Game createGame(Long id, String genre, String name) {
        return Game.builder()
                .id(checkParameter(id))
                .genre(checkParameter(genre))
                .name(checkParameter(name))
                .build();
    }
}
