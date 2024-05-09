package com.bagas.constants;

public class ReservationSQLQueries {

    public static final String FIND_ALL_LOCATIONS = "SELECT id, user_id, computer_id, " +
            "date, time FROM reservations";

    public static final String FIND_RESERVATION_BY_ID = "SELECT id, user_id, computer_id, " +
            "date, time FROM reservations WHERE id = ?";

    public static final String INSERT_RESERVATION = "INSERT INTO reservations(user_id, computer_id, date, time) " +
            "VALUES (?, ?, ?, ?)";

    public static final String UPDATE_RESERVATION = "UPDATE reservations set user_id = ?, " +
            "computer_id = ?, date = ?, time = ? WHERE id = ?";

    public static final String DELETE_RESERVATION = "DELETE FROM reservations WHERE id = ?";
}