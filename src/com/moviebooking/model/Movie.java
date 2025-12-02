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


    public abstract double calculatePrice();


    public String getTitle() {
        return title;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getDetails() {
        return "Movie: " + title + " (" + durationMinutes + " mins)";
    }
}