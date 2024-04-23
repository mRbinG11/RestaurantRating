package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.example.demo.entities.Review;

public class ReviewRepository {
    private final Map<Long, Review> reviewMap;
    private Long autoIncrement = 1L;

    public ReviewRepository() {
        reviewMap = new HashMap<Long, Review>();
    }

    public Review save(Review review) {
        Review r = new Review(autoIncrement, review.getRating(), review.getUserId(),
                review.getRestaurantId(), review.getDescription());
        reviewMap.put(autoIncrement++, r);
        return r;
    }

    public Review edit(Review review) {
        reviewMap.put(review.getId(), review);
        return review;
    }

    public boolean existsById(Long id) {
        return reviewMap.containsKey(id);
    }

    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(reviewMap.get(id));
    }

    public List<Review> findAll() {
        return reviewMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Review> findByUserIdAndRestaurantId(Long userId, Long restaurantId) {
        List<Review> rList = findAll();
        for (Review review : rList) {
            if (review.getUserId() == userId && review.getRestaurantId() == restaurantId)
                return Optional.of(review);
        }
        return Optional.empty();
    }

    public List<Review> findAllByRestaurantId(Long restaurantId) {
        List<Review> allReviews = findAll();
        Predicate<Review> byRestaurantId = r -> r.getRestaurantId() == restaurantId;
        List<Review> restaurantReviews =
                allReviews.stream().filter(byRestaurantId).collect(Collectors.toList());
        return restaurantReviews;
    }
}
