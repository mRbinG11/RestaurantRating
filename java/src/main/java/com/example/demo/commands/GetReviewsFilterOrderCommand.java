package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Review;
import com.example.demo.services.ReviewService;

public class GetReviewsFilterOrderCommand implements ICommand {
    private final ReviewService reviewService;

    public GetReviewsFilterOrderCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long restaurantId = Long.parseLong(tokens.get(1));
        Integer low = Integer.parseInt(tokens.get(2));
        Integer high = Integer.parseInt(tokens.get(3));
        String order = tokens.get(4);
        List<Review> rList = reviewService.getReviewsFilterOrder(restaurantId, low, high, order);
        System.out.println(rList);
    }
}
