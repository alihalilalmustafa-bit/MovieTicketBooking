package com.moviebooking.model;

public abstract class Movie {
    private String title;
    private int durationMinutes;
    private double basePrice;

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


    public double getBasePrice() {
        return basePrice;
    }


    public abstract double calculatePrice();
}