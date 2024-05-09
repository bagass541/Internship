package com.bagas.repositories.interfaces;

import com.bagas.entities.Game;

import java.util.List;

public interface GameRepository extends CRUDRepository<Game> {

    List<Game> findTheMostPopular();
}
