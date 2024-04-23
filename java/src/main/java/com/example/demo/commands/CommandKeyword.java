package com.example.demo.commands;

public enum CommandKeyword {
    // Register Command Keywords
    REGISTER_RESTAURANT("REGISTER_RESTAURANT"), REGISTER_USER("REGISTER_USER"), ADD_RATING(
            "ADD_RATING"), ADD_REVIEW("ADD_REVIEW"), GET_REVIEWS(
                    "GET_REVIEWS"), GET_REVIEWS_FILTER_ORDER(
                            "GET_REVIEWS_FILTER_ORDER"), DESCRIBE_RESTAURANT(
                                    "DESCRIBE_RESTAURANT"), LIST_RESTAURANT("LIST_RESTAURANT");

    private final String name;

    private CommandKeyword(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
