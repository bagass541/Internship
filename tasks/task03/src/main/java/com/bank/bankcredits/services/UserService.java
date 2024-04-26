package com.bank.bankcredits.services;

import java.io.IOException;

import com.bank.bankcredits.entities.User;
import com.bank.bankcredits.exceptions.UserNotFoundException;
import com.bank.bankcredits.repositories.UserRepository;

public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getById(long id) throws IOException, UserNotFoundException {
		User user = userRepository.getById(id).get();
		
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		return user;
	}

	public User getByNameSecondName(String fullName) throws IOException, UserNotFoundException {
		User user = userRepository.getByNameSecondName(fullName).get();

		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}

	public User getByIdName(long id, String name) throws IOException, UserNotFoundException {
		User user = userRepository.getByIdName(id, name).get();

		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}
	
}
