package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ThreeDMovieTest {

    @Test
    void testCalculatePrice_AddsGlassesFee() {
        ThreeDMovie movie = new ThreeDMovie("Avatar", 120, 10.0, 5.0);

        double price = movie.calculatePrice();


        assertEquals(15.0, price, "Price should include glasses fee");
    }

    @Test
    void testGetGlassesPrice() {
        ThreeDMovie movie = new ThreeDMovie("Avatar", 120, 10.0, 2.5);
        assertEquals(2.5, movie.getGlassesPrice());
    }
}