package com.example.demo.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(String name) {
        Restaurant r = new Restaurant(name);
        return restaurantRepository.save(r);
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant a, Restaurant b) {
                return Double.compare(b.getAvgRating(), a.getAvgRating());
            }
        });
        return restaurants;
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Restaurant with id: " + id + " not found!"));
    }

    public String describeRestaurant(Long id) {
        Restaurant r = getRestaurant(id);
        return "Restaurant [id=" + id + ", name=" + r.getName() + ", rating=" + r.getAvgRating()
                + "]";
    }

    public void updateAvgRating(Long id, Double avgRating) {
        Restaurant r = getRestaurant(id);
        r.setAvgRating(avgRating);
    }
}
