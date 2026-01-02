package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    // --- TEST 1: FRONT ROW DISCOUNT ---
    @Test
    void testFrontRowDiscount() {
        // 1. Setup: Use TwoDMovie instead of Movie
        TwoDMovie movie = new TwoDMovie("Regular Movie", 100, 20.0); // Base $20
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Customer cust = new Customer("TestUser", "test@test.com", false, false);

        // Pick Seat "A1" (The number is 1, which is <= 2)
        Seat frontSeat = show.getSeats().get(0);

        // 2. Execute
        Booking booking = new Booking(cust, show, frontSeat);

        // 3. Assert
        assertEquals(18.0, booking.getFinalPrice(), "Seats in Col 1 or 2 should get -$2.00 discount");
    }

    // --- TEST 2: VIP BACK ROW FEE ---
    @Test
    void testVipBackRowFee() {
        // 1. Setup: Use TwoDMovie instead of Movie
        TwoDMovie movie = new TwoDMovie("Regular Movie", 100, 20.0);
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Customer cust = new Customer("TestUser", "test@test.com", false, false);

        // Pick Seat "A6" (The number is 6, which is >= 5)
        Seat vipSeat = show.getSeats().get(5);

        // 2. Execute
        Booking booking = new Booking(cust, show, vipSeat);

        // 3. Assert
        assertEquals(23.0, booking.getFinalPrice(), "Seats in Col 5 or 6 should get +$3.00 fee");
    }

    // --- TEST 3: CANCELLATION LOGIC ---
    @Test
    void testCancelMethod() {
        // 1. Setup: Use TwoDMovie instead of Movie
        TwoDMovie movie = new TwoDMovie("Test Movie", 90, 10.0);
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Seat seat = show.getSeats().get(0);
        Customer cust = new Customer("Test", "test@test.com", false, false);

        Booking booking = new Booking(cust, show, seat);
        booking.confirmBooking();

        // Pre-check
        assertFalse(seat.isAvailable(), "Seat should be busy immediately after booking");

        // 2. Execute
        booking.cancel();

        // 3. Assert
        assertTrue(seat.isAvailable(), "Seat should be free again after cancel()");
    }
}