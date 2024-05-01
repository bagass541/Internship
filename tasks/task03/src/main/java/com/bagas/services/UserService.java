package com.bagas.services;

import java.io.IOException;

import com.bagas.entities.User;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.repositories.UserRepository;
import com.bagas.repositories.UserRepositoryImpl;

public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

	public User getById(long id) throws IOException, UserNotFoundException {
        return userRepository.getById(id)
				.orElseThrow(() -> new UserNotFoundException("User isn't found"));
	}

	public User getByNameSecondName(String fullName) throws IOException, UserNotFoundException {
        return userRepository.getByNameSecondName(fullName)
				.orElseThrow(() -> new UserNotFoundException("User isn't found"));
	}

	public User getByIdName(long id, String name) throws IOException, UserNotFoundException {
        return userRepository.getByIdName(id, name)
				.orElseThrow(() -> new UserNotFoundException("User isn't found"));
	}
}
