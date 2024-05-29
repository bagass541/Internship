package com.bagas.repositories.impl;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Game;
import com.bagas.mappers.GameMapper;
import com.bagas.repositories.interfaces.GameRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bagas.constants.CommonConstants.INDEX_OF_FIRST_PARAMETER;
import static com.bagas.constants.CommonConstants.SOMETHING_IS_WRONG;
import static com.bagas.constants.GameSQLQueries.*;

public class GameRepositoryImp implements GameRepository {

    private final String GENRE_PROPERTY = "genre";

    private final String ID_PROPERTY = "id";

    private final String NAME_PROPERTY = "name";

    private ConnectionPool connectionPool;

    public GameRepositoryImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Game> findTheMostPopular() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_THE_MOST_POPULAR_GAME)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Game> games = new ArrayList<>();

            while (resultSet.next()) {
                Game game = createGame(resultSet);
                games.add(game);
            }

            connectionPool.releaseConnection(connection);
            return games;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public List<Game> findAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GAMES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Game> games = new ArrayList<>();

            while (resultSet.next()) {
                Game game = createGame(resultSet);
                games.add(game);
            }

            connectionPool.releaseConnection(connection);
            return games;

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public Optional<Game> findById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_GAME_BY_ID)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Game game = createGame(resultSet);
                connectionPool.releaseConnection(connection);
                return Optional.of(game);
            }

        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
        return Optional.empty();
    }

    @Override
    public void save(Game game) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_GAME, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(game, preparedStatement, false);
            preparedStatement.execute();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                game.setId(generatedKey.getLong(INDEX_OF_FIRST_PARAMETER));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void update(Game game) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME)) {
            setPreparedStatementParameters(game, preparedStatement, true);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME)) {
            preparedStatement.setLong(INDEX_OF_FIRST_PARAMETER, id);
            preparedStatement.execute();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(SOMETHING_IS_WRONG);
        }
    }

    private Game createGame(ResultSet resultSet) throws SQLException {
        return GameMapper.createGame(resultSet.getLong(ID_PROPERTY),
                resultSet.getString(GENRE_PROPERTY),
                resultSet.getString(NAME_PROPERTY));
    }

    private void setPreparedStatementParameters(Game game, PreparedStatement preparedStatement,
                                                boolean isUpdate) throws SQLException {

        int increment = INDEX_OF_FIRST_PARAMETER;

        preparedStatement.setString(increment++, game.getGenre());
        preparedStatement.setString(increment++, game.getName());

        if (isUpdate) {
            preparedStatement.setLong(increment, game.getId());
        }
    }
}
