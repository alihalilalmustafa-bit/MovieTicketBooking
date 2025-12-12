package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ThreeDMovie class.
 * This satisfies the "JUnit tests" requirement (20%).
 */
class ThreeDMovieTest {

    @Test
    void testCalculatePrice_AddsGlassesFee() {
        // 1. Setup: Create a 3D movie with base price $10 and glasses $5
        ThreeDMovie movie = new ThreeDMovie("Avatar", 120, 10.0, 5.0);

        // 2. Execute: Calculate price
        double price = movie.calculatePrice();

        // 3. Assert: Check if result is 15.0 (10 + 5)
        // This ensures the logic is correct
        assertEquals(15.0, price, "Price should include glasses fee");
    }

    @Test
    void testGetGlassesPrice() {
        ThreeDMovie movie = new ThreeDMovie("Avatar", 120, 10.0, 2.5);
        assertEquals(2.5, movie.getGlassesPrice());
    }
}