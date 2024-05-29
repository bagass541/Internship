package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Computer;
import com.bagas.entities.TypeComputer;
import com.bagas.mappers.ComputerMapper;
import com.bagas.repositories.interfaces.ComputerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.*;
import static com.bagas.constants.ComputerSQLQueries.*;

public class ComputerRepositoryImp implements ComputerRepository {

    private final String TYPE_PROPERTY = "type";

    private final String LOCATION_ID_PROPERTY = "location_id";

    private ConnectionPool connectionPool;

    public ComputerRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Computer> findByRoom(Integer room) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMPUTER_BY_ROOM)) {
            preparedStatement.setInt(INDEX_OF_FIRST_PARAMETER, room);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Computer> computers = new ArrayList<>();

            while (resultSet.next()) {
                Computer computer = createComputer(resultSet);
                computers.add(computer);
            }

            connectionPool.releaseConnection(connection);
            return computers;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public List<Computer> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COMPUTERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Computer> computers = new ArrayList<>();

            while (resultSet.next()) {
                Computer computer = createComputer(resultSet);
                connectionPool.releaseConnection(connection);
                computers.add(computer);
            }

            return computers;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Computer> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMPUTER_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Computer computer = createComputer(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(computer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Computer computer) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(computer, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                computer.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Computer computer) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPUTER)) {
            setPreparedStatementParameters(computer, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPUTER)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Computer createComputer(ResultSet resultSet) throws SQLException {
        return ComputerMapper.createComputer(resultSet.getLong(ID_PROPERTY),
                TypeComputer.valueOf(resultSet.getString(TYPE_PROPERTY)),
                resultSet.getLong(LOCATION_ID_PROPERTY));
    }

    private void setPreparedStatementParameters(Computer computer, PreparedStatement preparedStatement,
                                                boolean isUpdate) throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setObject(increment++, computer.getTypeComputer(), Types.OTHER);
        preparedStatement.setLong(increment++, computer.getLocationId());

        if (isUpdate) {
            preparedStatement.setLong(increment, computer.getId());
        }
    }
}
