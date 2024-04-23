package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.RestaurantService;

public class DescribeRestaurantCommand implements ICommand {
    private final RestaurantService restaurantService;

    public DescribeRestaurantCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long id = Long.parseLong(tokens.get(1));
        String res = restaurantService.describeRestaurant(id);
        System.out.println(res);
    }
}
