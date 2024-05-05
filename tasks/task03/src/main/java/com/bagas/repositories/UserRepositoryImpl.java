package com.bagas.repositories;

import com.bagas.entities.User;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final String ELEMENT_NAME = "users";

    private DBReader dbReader;

    public UserRepositoryImpl(DBReader dbReader) {
        this.dbReader = dbReader;
    }

    @Override
    public Optional<User> getById(Long id) throws IOException {
        JsonArray usersJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return usersJson.asList().stream()
                .map(user -> GsonConfigurator.getGson().fromJson(user, User.class))
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<User> getByFullName(String fullName) throws IOException {
        JsonArray usersJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return usersJson.asList().stream()
                .map(user -> GsonConfigurator.getGson().fromJson(user, User.class))
                .filter(user -> (user.getName() + " " + user.getSecondName()).equals(fullName))
                .findFirst();
    }

    @Override
    public Optional<User> getByIdName(Long id, String name) throws IOException {
        JsonArray usersJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return usersJson.asList().stream()
                .map(user -> GsonConfigurator.getGson().fromJson(user, User.class))
                .filter(user -> user.getName().equals(name) && user.getId() == id)
                .findFirst();
    }
}
