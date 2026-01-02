package com.moviebooking.model;

public abstract class Movie {
    private String title;
    private int durationMinutes;
    private double basePrice; // Private, so we need a getter!

    public Movie(String title, int durationMinutes, double basePrice) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.basePrice = basePrice;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    // ✅ THIS IS THE MISSING METHOD THAT FIXES THE ERROR
    public double getBasePrice() {
        return basePrice;
    }

    // Abstract method for Polymorphism
    public abstract double calculatePrice();
}