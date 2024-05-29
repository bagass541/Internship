package com.bagas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private String url;

    private String username;

    private String password;

    private List<Connection> connectionPool;

    private List<Connection> usedConnections = new ArrayList<>();

    private final static int INITIAL_POOL_SIZE = 10;

    public static ConnectionPool create(String url, String username, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, username, password));
        }

        return new ConnectionPool(url, username, password, pool);
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public ConnectionPool(String URL, String USERNAME, String PASSWORD, List<Connection> connectionPool) {
        this.url = URL;
        this.username = USERNAME;
        this.password = PASSWORD;
        this.connectionPool = connectionPool;
    }

    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
}
