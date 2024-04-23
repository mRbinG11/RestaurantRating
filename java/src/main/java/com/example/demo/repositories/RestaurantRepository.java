package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Restaurant;

public class RestaurantRepository {
    private final Map<Long, Restaurant> restaurantMap;
    private Long autoIncrement = 1L;

    public RestaurantRepository() {
        restaurantMap = new HashMap<Long, Restaurant>();
    }

    public Restaurant save(Restaurant restaurant) {
        Restaurant r = new Restaurant(autoIncrement, restaurant.getName());
        restaurantMap.put(autoIncrement++, r);
        return r;
    }

    public boolean existsById(Long id) {
        return restaurantMap.containsKey(id);
    }

    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    public List<Restaurant> findAll() {
        return restaurantMap.values().stream().collect(Collectors.toList());
    }
}
