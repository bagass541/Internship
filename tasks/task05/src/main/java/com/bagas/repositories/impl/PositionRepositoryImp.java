package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Position;
import com.bagas.mappers.PositionMapper;
import com.bagas.repositories.interfaces.PositionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.*;
import static com.bagas.constants.PositionSQLQueries.*;

public class PositionRepositoryImp implements PositionRepository {

    private final String NAME_PROPERTY = "name";

    private final String SALARY_PROPERTY = "salary";

    private ConnectionPool connectionPool;

    public PositionRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Position> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_POSITIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Position> positions = new ArrayList<>();

            while (resultSet.next()) {
                Position position = createPosition(resultSet);
                positions.add(position);
            }

            connectionPool.releaseConnection(connection);
            return positions;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Position> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_POSITION_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Position position = createPosition(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(position);
            }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Position position) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_POSITION, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(position, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                position.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Position position) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POSITION)) {
            setPreparedStatementParameters(position, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POSITION)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Position createPosition(ResultSet resultSet) throws SQLException {
        return PositionMapper.createPosition(resultSet.getLong(ID_PROPERTY),
                resultSet.getString(NAME_PROPERTY),
                resultSet.getBigDecimal(SALARY_PROPERTY));
    }

    private void setPreparedStatementParameters(Position position, PreparedStatement preparedStatement, boolean isUpdate)
            throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setString(increment++, position.getName());
        preparedStatement.setBigDecimal(increment++, position.getSalary());

        if (isUpdate) {
            preparedStatement.setLong(increment, position.getId());
        }
    }
}
