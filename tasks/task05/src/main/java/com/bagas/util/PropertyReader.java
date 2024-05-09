package com.bagas.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyReader {

    private final String CONNECTION_PROPERTIES_PATH = "connection.properties";

    public Properties readConnectionProperties()  {
        Properties connectionProperties = new Properties();

        InputStream in = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream(CONNECTION_PROPERTIES_PATH);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            connectionProperties.load(reader);
            return connectionProperties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
