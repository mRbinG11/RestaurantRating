package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepository;

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    public ReviewService(ReviewRepository reviewRepository, UserService userService,
            RestaurantService restaurantService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    public Review create(Integer rating, Long userId, Long restaurantId) {
        Review r = new Review(rating, userId, restaurantId);
        return reviewRepository.save(r);
    }

    public Review update(Review review, Integer rating) {
        review.setRating(rating);
        return reviewRepository.edit(review);
    }

    public Review add(Integer rating, Long userId, Long restaurantId) {
        userService.getUser(userId);
        restaurantService.getRestaurant(restaurantId);
        Review review = reviewRepository.findByUserIdAndRestaurantId(userId, restaurantId)
                .orElse(create(rating, userId, restaurantId));
        Review r = update(review, rating);
        List<Review> rList = getReviews(restaurantId, "RATING_DESC");
        List<Integer> ratings = new ArrayList<Integer>();
        for (Review rl : rList) {
            ratings.add(rl.getRating());
        }
        OptionalDouble avgRating = ratings.stream().mapToDouble(a -> a).average();
        restaurantService.updateAvgRating(restaurantId,
                avgRating.isPresent() ? avgRating.getAsDouble() : 0.0);
        return r;
    }

    public List<Review> getReviews(Long restaurantId, String order) {
        List<Review> restaurantReviews = reviewRepository.findAllByRestaurantId(restaurantId);
        switch (order) {
            case "RATING_ASC":
                Collections.sort(restaurantReviews, new Comparator<Review>() {
                    @Override
                    public int compare(Review a, Review b) {
                        return a.getRating() - b.getRating();
                    }
                });
                break;
            case "RATING_DESC":
                Collections.sort(restaurantReviews, new Comparator<Review>() {
                    @Override
                    public int compare(Review a, Review b) {
                        return b.getRating() - a.getRating();
                    }
                });
                break;
        }
        return restaurantReviews;
    }

    public List<Review> getReviewsFilterOrder(Long restaurantId, Integer low, Integer high,
            String order) {
        List<Review> restaurantReviews = getReviews(restaurantId, order);
        Predicate<Review> byLowHigh = r -> r.getRating() >= low && r.getRating() <= high;
        List<Review> filteredReviews =
                restaurantReviews.stream().filter(byLowHigh).collect(Collectors.toList());
        return filteredReviews;
    }
}
