package com.bagas.constants;

public class SessionSQLQueries {

    public static final String FIND_SESSION_BY_USERNAME = "SELECT sessions.id, user_id, employee_id, " +
            "game_id, computer_id, duration FROM sessions " +
            "JOIN users ON sessions.id = users.id " +
            "WHERE users.name = ?";

    public static final String FIND_ALL_SESSIONS = "SELECT id, user_id, employee_id, " +
            "game_id, computer_id, duration FROM sessions";

    public static final String FIND_SESSION_BY_ID = "SELECT id, user_id, employee_id, " +
            "game_id, computer_id, duration FROM sessions WHERE id = ?";

    public static final String INSERT_SESSION = "INSERT INTO sessions(user_id, employee_id, game_id, " +
            "computer_id, duration) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_SESSION = "UPDATE sessions set user_id = ?, employee_id = ?, " +
            "game_id = ?, computer_id = ?, duration = ? WHERE id = ?";

    public static final String DELETE_SESSION = "DELETE FROM sessions WHERE id = ?";
}
