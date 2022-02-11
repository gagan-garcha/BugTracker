package com.app.bugtracker.service;

import com.app.bugtracker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    List<User> findAllUsers();

    User saveUser(User user);
}
