package com.bagas.constants;

public class ComputerSQLQueries {

    public static final String FIND_COMPUTER_BY_ROOM = "SELECT computers.id, type, location_id FROM computers " +
            "JOIN locations ON locations.id = computers.id " +
            "WHERE locations.room = ?";

    public static final String FIND_ALL_COMPUTERS = "SELECT id, type, location_id FROM computers";

    public static final String FIND_COMPUTER_BY_ID = "SELECT id, type, location_id FROM computers WHERE id = ?";

    public static final String INSERT_COMPUTER = "INSERT INTO computers(type, location_id) VALUES (?, ?)";

    public static final String UPDATE_COMPUTER = "UPDATE computers set type = ?, location_id = ? WHERE id = ?";

    public static final String DELETE_COMPUTER = "DELETE FROM computers WHERE id = ?";
}
