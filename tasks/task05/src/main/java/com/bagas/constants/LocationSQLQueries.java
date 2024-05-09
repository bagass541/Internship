package com.bagas.constants;

public class LocationSQLQueries {

    public static final String FIND_ALL_LOCATIONS = "SELECT id, room, place FROM locations";

    public static final String FIND_LOCATION_BY_ID = "SELECT id, room, place FROM locations WHERE id = ?";

    public static final String INSERT_LOCATION = "INSERT INTO locations(room, place) VALUES (?, ?)";

    public static final String UPDATE_LOCATION = "UPDATE locations set room = ?, place = ? WHERE id = ?";

    public static final String DELETE_LOCATION = "DELETE FROM locations WHERE id = ?";
}
