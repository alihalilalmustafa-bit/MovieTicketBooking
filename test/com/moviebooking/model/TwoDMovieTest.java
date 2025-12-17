package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TwoDMovieTest {

    @Test
    void testCalculatePrice_ReturnsBasePrice() {
        // 1. Setup: Create a 2D movie with $12 price
        TwoDMovie movie = new TwoDMovie("Inception", 148, 12.0);

        // 2. Execute: Calculate price
        double price = movie.calculatePrice();

        // 3. Assert: Check if result is exactly 12.0 (No extra fees)
        assertEquals(12.0, price, "2D Movie should return base price without extra fees");
    }
}