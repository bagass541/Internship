package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.User;
import com.bagas.mappers.UserMapper;
import com.bagas.repositories.interfaces.UserRepository;

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
import static com.bagas.constants.UserSQLQueries.*;

public class UserRepositoryImp implements UserRepository {

    private final String NAME_PROPERTY = "name";

    private final String SECOND_NAME_PROPERTY = "second_name";

    private final String BIRTHDAY_PROPERTY = "birthday";

    private final String PHONE_NUMBER_PROPERTY = "phone_number";

    private ConnectionPool connectionPool;

    public UserRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<User> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }

            connectionPool.releaseConnection(connection);
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = createUser(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(User user) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(user, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                user.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            setPreparedStatementParameters(user, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return UserMapper.createUser(resultSet.getLong(ID_PROPERTY),
                resultSet.getString(NAME_PROPERTY),
                resultSet.getString(SECOND_NAME_PROPERTY),
                resultSet.getDate(BIRTHDAY_PROPERTY).toLocalDate(),
                resultSet.getString(PHONE_NUMBER_PROPERTY));
    }

    private void setPreparedStatementParameters(User user, PreparedStatement preparedStatement, boolean isUpdate)
            throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setString(increment++, user.getName());
        preparedStatement.setString(increment++, user.getSecondName());
        preparedStatement.setDate(increment++, Date.valueOf(user.getBirthday()));
        preparedStatement.setString(increment++, user.getPhoneNumber());

        if (isUpdate) {
            preparedStatement.setLong(increment, user.getId());
        }
    }
}
