package com.bagas.repositories;

import java.io.IOException;
import java.util.Optional;

import com.bagas.entities.User;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class UserRepositoryImpl implements UserRepository {

	private final String ELEMENT_NAME = "users";

	private DBReader dbReader;
	
	public UserRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public Optional<User> getById(long id) throws IOException {
		JsonArray usersJson = dbReader.getJsonArrayByName(ELEMENT_NAME);
		
		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			if(user.getId() == id) {
				return Optional.of(user);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<User> getByNameSecondName(String fullName) throws IOException {
		JsonArray usersJson =dbReader.getJsonArrayByName(ELEMENT_NAME);

		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			String fullNameCurrUser = user.getName() + " " + user.getSecondName();
			if (fullNameCurrUser.equals(fullName)) {
				return Optional.of(user);
			}
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getByIdName(long id, String name) throws IOException {
		JsonArray usersJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			if (user.getName().equals(name) && user.getId() == id) {
				return Optional.of(user);
			}
		}

		return Optional.empty();
	}
}
