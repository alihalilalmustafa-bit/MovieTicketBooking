package com.moviebooking.model;


public class ThreeDMovie extends Movie {


    private double glassesPrice;

    public ThreeDMovie(String title, int durationMinutes, double basePrice, double glassesPrice) {
        super(title, durationMinutes, basePrice);
        this.glassesPrice = glassesPrice;
    }


    @Override
    public double calculatePrice() {
        return getBasePrice() + glassesPrice;
    }

    public double getGlassesPrice() {
        return glassesPrice;
    }
}

