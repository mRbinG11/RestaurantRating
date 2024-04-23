package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Review;
import com.example.demo.services.ReviewService;

public class GetReviewsCommand implements ICommand {
    private final ReviewService reviewService;

    public GetReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long restaurantId = Long.parseLong(tokens.get(1));
        String order = tokens.get(2);
        List<Review> rList = reviewService.getReviews(restaurantId, order);
        System.out.println(rList);
    }
}
