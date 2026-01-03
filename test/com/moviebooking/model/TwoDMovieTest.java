package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TwoDMovieTest {

    @Test
    void testCalculatePrice_ReturnsBasePrice() {

        TwoDMovie movie = new TwoDMovie("Inception", 148, 12.0);

        double price = movie.calculatePrice();


        assertEquals(12.0, price, "2D Movie should return base price without extra fees");
    }
}