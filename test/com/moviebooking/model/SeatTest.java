package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void testSeatStartsAvailable() {
        Seat seat = new Seat('A', 1);
        assertTrue(seat.isAvailable(), "New seat should be available");
    }

    @Test
    void testBookingSuccess() {
        Seat seat = new Seat('A', 1);
        boolean result = seat.book();

        assertTrue(result, "Booking should return true for available seat");
        assertFalse(seat.isAvailable(), "Seat should be unavailable after booking");
    }

    @Test
    void testDoubleBookingFails() {
        Seat seat = new Seat('B', 2);
        seat.book();

        boolean result = seat.book();

        assertFalse(result, "Booking should fail if seat is already taken");
    }
}