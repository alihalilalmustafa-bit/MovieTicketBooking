package com.moviebooking.model;

/**
 * Represents a standard 2D movie experience.
 * This class extends the abstract Movie class to provide a concrete implementation
 * for movies that do not require special glasses or extra fees.
 * * <p>It implements the Polymorphism principle by defining the specific behavior
 * of price calculation for standard movies.</p>
 */
public class TwoDMovie extends Movie {

    /**
     * Constructs a new 2D Movie.
     *
     * @param title           The title of the movie.
     * @param durationMinutes The total duration of the movie in minutes.
     * @param basePrice       The base ticket price.
     */
    public TwoDMovie(String title, int durationMinutes, double basePrice) {
        super(title, durationMinutes, basePrice);
    }

    /**
     * Calculates the final price for a 2D movie ticket.
     * Since 2D movies have no extra surcharges, it simply returns the base price.
     *
     * @return The standard base price of the movie.
     */
    @Override
    public double calculatePrice() {
        // No extra fees for 2D movies
        return getBasePrice();
    }
}