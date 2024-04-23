package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.User;

public class UserRepository {
    private final Map<Long, User> userMap;
    private Long autoIncrement = 1L;

    public UserRepository() {
        userMap = new HashMap<Long, User>();
    }

    public User save(User user) {
        User u = new User(autoIncrement, user.getName());
        userMap.put(autoIncrement++, u);
        return u;
    }

    public boolean existsById(Long id) {
        return userMap.containsKey(id);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public List<User> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    }
}
