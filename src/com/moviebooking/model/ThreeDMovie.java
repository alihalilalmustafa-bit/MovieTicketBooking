package com.moviebooking.model;


public class ThreeDMovie extends Movie {

    // Encapsulation: Private field
    private double glassesPrice;

    public ThreeDMovie(String title, int durationMinutes, double basePrice, double glassesPrice) {
        super(title, durationMinutes, basePrice); // Inheritance call
        this.glassesPrice = glassesPrice;
    }

    // Polymorphism: Override calculatePrice as required in Step 3
    @Override
    public double calculatePrice() {
        return getBasePrice() + glassesPrice;
    }

    public double getGlassesPrice() {
        return glassesPrice;
    }
}