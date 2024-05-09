package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Session;
import com.bagas.mappers.SessionMapper;
import com.bagas.repositories.interfaces.SessionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.*;
import static com.bagas.constants.SessionSQLQueries.*;


public class SessionRepositoryImp implements SessionRepository {

    private final String USER_ID_PROPERTY = "user_id";

    private final String EMPLOYEE_ID_PROPERTY = "employee_id";

    private final String GAME_ID_PROPERTY = "game_id";

    private final String COMPUTER_ID_PROPERTY = "computer_id";

    private final String DURATION_PROPERTY = "duration";

    private ConnectionPool connectionPool;

    public SessionRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Session> findByUserName(String name) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SESSION_BY_USERNAME)) {
            preparedStatement.setString(INDEX_OF_FIRST_PARAMETER, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Session> sessions = new ArrayList<>();

            while (resultSet.next()) {
                Session session = createSession(resultSet);
                sessions.add(session);
            }

            connectionPool.releaseConnection(connection);
            return sessions;
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public List<Session> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SESSIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Session> sessions = new ArrayList<>();

            while (resultSet.next()) {
                Session session = createSession(resultSet);
                sessions.add(session);
            }

            connectionPool.releaseConnection(connection);
            return sessions;
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Session> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SESSION_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Session session = createSession(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(session);
            }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Session session) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_SESSION, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(session, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                session.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Session session) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SESSION)) {
            setPreparedStatementParameters(session, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SESSION)) {

            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Session createSession(ResultSet resultSet) throws SQLException {
        return SessionMapper.createSession(resultSet.getLong(ID_PROPERTY),
                resultSet.getLong(USER_ID_PROPERTY),
                resultSet.getLong(EMPLOYEE_ID_PROPERTY), resultSet.getLong(GAME_ID_PROPERTY),
                resultSet.getLong(COMPUTER_ID_PROPERTY),
                resultSet.getTime(DURATION_PROPERTY).toLocalTime());
    }

    private void setPreparedStatementParameters(Session session, PreparedStatement preparedStatement, boolean isUpdate)
            throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setLong(increment++, session.getUserId());
        preparedStatement.setLong(increment++, session.getEmployeeId());
        preparedStatement.setLong(increment++, session.getGameId());
        preparedStatement.setLong(increment++, session.getComputerId());
        preparedStatement.setTime(increment++, Time.valueOf(session.getDuration()));

        if (isUpdate) {
            preparedStatement.setLong(increment, session.getId());
        }
    }
}
