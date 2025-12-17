package com.moviebooking.model;

/**
 * Represents a standard 2D movie.
 * Inherits from Movie but does not add extra costs.
 */
public class TwoDMovie extends Movie {

    public TwoDMovie(String title, int durationMinutes, double basePrice) {
        super(title, durationMinutes, basePrice);
    }

    /**
     * Implementation of the abstract method.
     * For 2D movies, the price is just the base price.
     */
    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}