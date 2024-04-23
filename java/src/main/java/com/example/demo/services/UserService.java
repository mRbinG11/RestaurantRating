package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name) {
        User u = new User(name);
        return userRepository.save(u);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id: " + id + " not found!"));
    }
}
