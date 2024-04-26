package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.util.Optional;

import com.bank.bankcredits.entities.User;

public interface UserRepository {
	
	Optional<User> getById(long id) throws IOException;
	
	Optional<User> getByNameSecondName(String fullName) throws IOException;
	
	Optional<User> getByIdName(long id, String name) throws IOException;
}
