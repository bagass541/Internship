package com.bagas.services;

import com.bagas.entities.Game;
import com.bagas.entities.Session;
import com.bagas.exceptions.GameNotFoundException;
import com.bagas.exceptions.SessionNotFoundException;
import com.bagas.repositories.interfaces.GameRepository;
import com.bagas.repositories.interfaces.SessionRepository;

import java.util.List;

import static com.bagas.constants.ExceptionMessageConstants.GAME_NOT_FOUND_MESSAGE;
import static com.bagas.constants.ExceptionMessageConstants.SESSION_NOT_FOUND_MESSAGE;

public class SessionService {



    private SessionRepository sessionRepository;

    private GameRepository gameRepository;

    public SessionService(GameRepository gameRepository, SessionRepository sessionRepository) {
        this.gameRepository = gameRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getSessionsByUserName(String name) {
        return sessionRepository.findByUserName(name);
    }

    public List<Game> getTheMostPopularGame() {
        return gameRepository.findTheMostPopular();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) throws SessionNotFoundException {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(SESSION_NOT_FOUND_MESSAGE));
    }

    public Game getGameById(Long id) throws GameNotFoundException {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE));
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void updateSession(Session session) {
        sessionRepository.update(session);
    }

    public void updateGame(Game game) {
        gameRepository.update(game);
    }

    public void deleteSessionById(Long id) {
        sessionRepository.deleteById(id);
    }

    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
}
