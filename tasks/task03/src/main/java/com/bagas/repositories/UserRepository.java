package com.bagas.repositories;

import com.bagas.entities.User;

import java.io.IOException;
import java.util.Optional;

public interface UserRepository {
	
	Optional<User> getById(Long id) throws IOException;
	
	Optional<User> getByFullName(String fullName) throws IOException;
	
	Optional<User> getByIdName(Long id, String name) throws IOException;
}
