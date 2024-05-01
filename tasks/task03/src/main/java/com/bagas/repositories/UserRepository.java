package com.bagas.repositories;

import com.bagas.entities.User;

import java.io.IOException;
import java.util.Optional;

public interface UserRepository {
	
	Optional<User> getById(long id) throws IOException;
	
	Optional<User> getByNameSecondName(String fullName) throws IOException;
	
	Optional<User> getByIdName(long id, String name) throws IOException;
}
