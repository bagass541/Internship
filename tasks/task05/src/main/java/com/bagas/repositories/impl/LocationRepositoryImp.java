package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Location;
import com.bagas.mappers.LocationMapper;
import com.bagas.repositories.interfaces.LocationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.*;
import static com.bagas.constants.LocationSQLQueries.*;

public class LocationRepositoryImp implements LocationRepository {

    private final String ROOM_PROPERTY = "room";

    private final String PLACE_PROPERTY = "place";

    private ConnectionPool connectionPool;

    public LocationRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Location> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_LOCATIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Location> locations = new ArrayList<>();

            while (resultSet.next()) {
                Location location = createLocation(resultSet);
                locations.add(location);
            }

            connectionPool.releaseConnection(connection);
            return locations;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Location> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOCATION_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Location location = createLocation(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(location);
            }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Location location) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_LOCATION, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(location, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                location.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Location location) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOCATION)) {
            setPreparedStatementParameters(location, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOCATION)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Location createLocation(ResultSet resultSet) throws SQLException {
        return LocationMapper.createLocation(resultSet.getLong(ID_PROPERTY),
                resultSet.getInt(ROOM_PROPERTY),
                resultSet.getInt(PLACE_PROPERTY));
    }

    private void setPreparedStatementParameters(Location location, PreparedStatement preparedStatement,
                                                boolean isUpdate) throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setLong(increment++, location.getRoom());
        preparedStatement.setLong(increment++, location.getPlace());

        if (isUpdate) {
            preparedStatement.setLong(increment, location.getId());
        }
    }
}
