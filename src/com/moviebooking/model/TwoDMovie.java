package com.moviebooking.model;


public class TwoDMovie extends Movie {


    public TwoDMovie(String title, int durationMinutes, double basePrice) {
        super(title, durationMinutes, basePrice);
    }


    @Override
    public double calculatePrice() {
        // No extra fees for 2D movies
        return getBasePrice();
    }
}