package com.bagas.services;

import com.bagas.entities.Settings;
import com.bagas.entities.User;
import com.bagas.entities.enums.ShowForType;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.repositories.UserRepository;
import com.bagas.repositories.UserRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository;

    private final String USER_NOT_FOUNT_MESSAGE = "User isn't found";

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(Settings settings) {
        ShowForType type = settings.getShowFor().getType();
        List<String> usersInfo = settings.getShowFor().getUsers();

        if (type == ShowForType.ID) {
            List<Long> ids = usersInfo.stream()
                    .map(Long::parseLong)
                    .toList();

            return getUsersById(ids);
        } else if (type == ShowForType.NAME) {
            return getUsersByFullName(usersInfo);
        } else {
            return getUsersByIdName(usersInfo);
        }
    }

    private List<User> getUsersById(List<Long> ids) {
        return ids.stream()
                .map(id -> {
                    try {
                        return userRepository.getById(id)
                                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUNT_MESSAGE));
                    } catch (UserNotFoundException | IOException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<User> getUsersByFullName(List<String> fullNames) {
        return fullNames.stream()
                .map(fullName -> {
                    try {
                        return userRepository.getByFullName(fullName)
                                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUNT_MESSAGE));
                    } catch (IOException | UserNotFoundException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    private List<User> getUsersByIdName(List<String> usersInfo) {
        return usersInfo.stream()
                .map(info -> {
                    try {
                        String[] data = info.split("/");
                        return userRepository.getByIdName(Long.parseLong(data[0]), data[1])
                                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUNT_MESSAGE));
                    } catch (IOException | UserNotFoundException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
