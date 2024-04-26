package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.util.Optional;

import com.bank.bankcredits.entities.User;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class UserRepositoryImpl implements UserRepository {
	
	private DBReader dbReader;
	
	public UserRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public Optional<User> getById(long id) throws IOException {
		JsonArray usersJson = dbReader.getJsonArrayByName("users");
		
		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			if(user.getId() == id) {
				return Optional.ofNullable(user);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<User> getByNameSecondName(String fullName) throws IOException {
		JsonArray usersJson =dbReader.getJsonArrayByName("users");

		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			String fullNameCurrUser = user.getName() + " " + user.getSecondName();
			if (fullNameCurrUser.equals(fullName)) {
				return Optional.ofNullable(user);
			}
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getByIdName(long id, String name) throws IOException {
		JsonArray usersJson = dbReader.getJsonArrayByName("users");

		for (JsonElement element : usersJson) {
			User user = GsonConfigurator.getGson().fromJson(element, User.class);
			if (user.getName().equals(name) && user.getId() == id) {
				return Optional.ofNullable(user);
			}
		}

		return Optional.empty();
	}

}
