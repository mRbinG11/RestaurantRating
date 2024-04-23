package com.example.demo.entities;

public class Review {
    private Long id;
    private Integer rating;
    private Long userId;
    private Long restaurantId;
    private String description;

    public Review(Long id, Integer rating, Long userId, Long restaurantId, String description) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.description = description;
    }

    public Review(Integer rating, Long userId, Long restaurantId, String description) {
        this(null, rating, userId, restaurantId, description);
    }

    public Review(Integer rating, Long userId, Long restaurantId) {
        this(rating, userId, restaurantId, null);
    }

    public Review(Long id, Integer rating, Long userId, Long restaurantId) {
        this(id, rating, userId, restaurantId, null);
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getDescription() {
        return description;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + "]";
    }
}
