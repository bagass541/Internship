package com.bagas.services;

import com.bagas.entities.User;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.repositories.interfaces.UserRepository;

import java.util.List;

import static com.bagas.constants.ExceptionMessageConstants.USER_NOT_FOUND_MESSAGE;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
