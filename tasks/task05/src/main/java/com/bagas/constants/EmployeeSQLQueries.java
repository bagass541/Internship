package com.bagas.constants;

public class EmployeeSQLQueries {

    public static final String FIND_ALL_EMPLOYEES = "SELECT id, name, second_name, " +
            "birthday, position_id, phone_number " +
            "FROM employees";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT id, name, second_name, " +
            "birthday, position_id, phone_number " +
            "FROM employees WHERE id = ?";

    public static final String INSERT_EMPLOYEE = "INSERT INTO employees(name, second_name, " +
            "birthday, position_id, phone_number) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_EMPLOYEE = "UPDATE employees set name = ?, second_name = ?, birthday = ?, " +
            "position_id = ?, phone_number = ? WHERE id = ?";

    public static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";
}
