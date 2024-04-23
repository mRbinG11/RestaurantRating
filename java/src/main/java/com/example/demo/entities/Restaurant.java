package com.example.demo.entities;

public class Restaurant {
    private Long id;
    private String name;
    private Double avgRating;

    public Restaurant(Long id, String name) {
        this.id = id;
        this.name = name;
        this.avgRating = 0.0;
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getAvgRating() {
        return this.avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
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
        Restaurant other = (Restaurant) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Restaurant [id=" + id + "]";
    }
}
