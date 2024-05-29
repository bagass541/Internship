package com.bagas.constants;

public class GameSQLQueries {

    public static final String FIND_THE_MOST_POPULAR_GAME = "SELECT games.id, genre, name, " +
            "count(games.id) as counts FROM games " +
            "JOIN sessions ON sessions.game_id = games.id " +
            "GROUP BY games.id " +
            "ORDER BY counts DESC " +
            "LIMIT 3";

    public static final String FIND_ALL_GAMES = "SELECT id, genre, name FROM games";

    public static final String FIND_GAME_BY_ID = "SELECT id, genre, name FROM games WHERE id = ?";

    public static final String INSERT_GAME = "INSERT INTO games(genre, name) VALUES (?, ?)";

    public static final String UPDATE_GAME = "UPDATE games set genre = ?, name = ? WHERE id = ?";

    public static final String DELETE_GAME = "DELETE FROM games WHERE id = ?";

}
