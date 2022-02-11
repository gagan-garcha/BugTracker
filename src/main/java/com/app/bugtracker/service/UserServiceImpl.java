package com.app.bugtracker.service;

import com.app.bugtracker.model.User;
import com.app.bugtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository mUserRepository;

    public UserServiceImpl(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return mUserRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return mUserRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return mUserRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return mUserRepository.save(user);
    }
}
