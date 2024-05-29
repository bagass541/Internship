package com.bagas.constants;

public class UserSQLQueries {

    public static final String FIND_ALL_USERS = "SELECT id, name, second_name, " +
            "birthday, phone_number FROM users";

    public static final String FIND_USER_BY_ID = "SELECT id, name, second_name, " +
            "birthday, phone_number FROM users WHERE id = ?";

    public static final String INSERT_USER = "INSERT INTO users(name, second_name, birthday, phone_number) " +
            "VALUES (?, ?, ?, ?)";

    public static final String UPDATE_USER = "UPDATE users set name = ?, second_name = ?, " +
            "birthday = ?, phone_number = ? WHERE id = ?";

    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
}
