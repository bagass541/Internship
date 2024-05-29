package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Reservation;
import com.bagas.mappers.ReservationMapper;
import com.bagas.repositories.interfaces.ReservationRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.INDEX_OF_FIRST_PARAMETER;
import static com.bagas.constants.CommonConstants.SOMETHING_IS_WRONG;
import static com.bagas.constants.ReservationSQLQueries.*;

public class ReservationRepositoryImp implements ReservationRepository {

    private final String USER_ID_PROPERTY = "user_id";

    private final String COMPUTER_ID_PROPERTY = "computer_id";

    private final String DATE_PROPERTY = "date";

    private final String TIME_PROPERTY = "time";

    private final String ID_PROPETY = "id";

    private ConnectionPool connectionPool;

    public ReservationRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Reservation> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_LOCATIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> reservations = new ArrayList<>();

            while (resultSet.next()) {
                Reservation reservation = createReservation(resultSet);
                reservations.add(reservation);
            }

            connectionPool.releaseConnection(connection);
            return reservations;
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Reservation reservation = createReservation(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }

        return Optional.empty();
    }

    @Override
    public void save(Reservation reservation) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(reservation, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                reservation.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Reservation reservation) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATION)) {
            setPreparedStatementParameters(reservation, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION)) {

            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Reservation createReservation(ResultSet resultSet) throws SQLException {
        return ReservationMapper.createReservation(resultSet.getLong(ID_PROPETY),
                resultSet.getLong(USER_ID_PROPERTY),
                resultSet.getLong(COMPUTER_ID_PROPERTY),
                resultSet.getDate(DATE_PROPERTY).toLocalDate(),
                resultSet.getTime(TIME_PROPERTY).toLocalTime());
    }

    private void setPreparedStatementParameters(Reservation reservation, PreparedStatement preparedStatement,
                                                boolean isUpdate) throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setLong(increment++, reservation.getUserId());
        preparedStatement.setLong(increment++, reservation.getComputerId());
        preparedStatement.setDate(increment++, Date.valueOf(reservation.getDate()));
        preparedStatement.setTime(increment++, Time.valueOf(reservation.getTime()));

        if (isUpdate) {
            preparedStatement.setLong(increment, reservation.getId());
        }
    }
}
