package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Review;
import com.example.demo.services.ReviewService;

public class AddRatingCommand implements ICommand {
    private final ReviewService reviewService;

    public AddRatingCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Integer rating = Integer.parseInt(tokens.get(1));
        Long userId = Long.parseLong(tokens.get(2));
        Long restaurantId = Long.parseLong(tokens.get(3));
        Review r = reviewService.add(rating, userId, restaurantId);
        System.out.println(r + " added successfully for Restaurant [id=" + restaurantId + "] by User [id="
                + userId + "]!");
    }
}
