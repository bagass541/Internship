package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Employee;
import com.bagas.mappers.EmployeeMapper;
import com.bagas.repositories.interfaces.EmployeeRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.*;
import static com.bagas.constants.EmployeeSQLQueries.*;

public class EmployeeRepositoryImp implements EmployeeRepository {

    private final String NAME_PROPERTY = "name";

    private final String SECOND_NAME_PROPERTY = "second_name";

    private final String BIRTHDAY_PROPERTY = "birthday";

    private final String POSITION_ID_PROPERTY = "position_id";

    private final String PHONE_NUMBER_PROPERTY = "phone_number";

    private ConnectionPool connectionPool;

    public EmployeeRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Employee> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EMPLOYEES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
                List<Employee> employees = new ArrayList<>();

                while (resultSet.next()) {
                    Employee employee = createEmployee(resultSet);
                    employees.add(employee);
                }

                connectionPool.releaseConnection(connection);
                return employees;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Employee employee = createEmployee(resultSet);
                    connectionPool.releaseConnection(connection);
                    return Optional.of(employee);
                }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Employee employee) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(employee, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                employee.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Employee employee) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            setPreparedStatementParameters(employee, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        return EmployeeMapper.createEmployee(resultSet.getLong(ID_PROPERTY),
                resultSet.getString(NAME_PROPERTY),
                resultSet.getString(SECOND_NAME_PROPERTY),
                resultSet.getDate(BIRTHDAY_PROPERTY).toLocalDate(),
                resultSet.getLong(POSITION_ID_PROPERTY), resultSet.getString(PHONE_NUMBER_PROPERTY));
    }

    private void setPreparedStatementParameters(Employee employee, PreparedStatement preparedStatement,
                                                boolean isUpdate) throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setString(increment++, employee.getName());
        preparedStatement.setString(increment++, employee.getSecondName());
        preparedStatement.setDate(increment++, Date.valueOf(employee.getBirthday()));
        preparedStatement.setLong(increment++, employee.getPositionId());
        preparedStatement.setString(increment++, employee.getPhoneNumber());

        if (isUpdate) {
            preparedStatement.setLong(increment, employee.getId());
        }
    }
}
