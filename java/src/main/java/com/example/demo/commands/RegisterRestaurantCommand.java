package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.RestaurantService;

public class RegisterRestaurantCommand implements ICommand {
    private final RestaurantService restaurantService;

    public RegisterRestaurantCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String name = tokens.get(1);
        Restaurant r = restaurantService.create(name);
        System.out.println(r);
    }
}
