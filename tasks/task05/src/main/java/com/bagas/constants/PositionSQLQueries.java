package com.bagas.constants;

public class PositionSQLQueries {

    public static final String FIND_ALL_POSITIONS = "SELECT id, name, salary FROM positions";

    public static final String FIND_POSITION_BY_ID = "SELECT id, name, salary FROM positions WHERE id = ?";

    public static final String INSERT_POSITION = "INSERT INTO positions(name, salary) VALUES (?, ?)";

    public static final String UPDATE_POSITION = "UPDATE positions set name = ?, salary = ? WHERE id = ?";

    public static final String DELETE_POSITION = "DELETE FROM positions WHERE id = ?";
}
