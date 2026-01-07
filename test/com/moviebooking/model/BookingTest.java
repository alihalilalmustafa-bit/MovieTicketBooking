package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {


    @Test
    void testFrontRowDiscount() {

        TwoDMovie movie = new TwoDMovie("Regular Movie", 100, 20.0); // Base $20
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Customer cust = new Customer("TestUser", "test@test.com", false, false);


        Seat frontSeat = show.getSeats().get(0);


        Booking booking = new Booking(cust, show, frontSeat);


        assertEquals(18.0, booking.getFinalPrice(), "Seats in Col 1 or 2 should get -$2.00 discount");
    }


    @Test
    void testVipBackRowFee() {
        TwoDMovie movie = new TwoDMovie("Regular Movie", 100, 20.0);
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Customer cust = new Customer("TestUser", "test@test.com", false, false);


        Seat vipSeat = show.getSeats().get(5);


        Booking booking = new Booking(cust, show, vipSeat);


        assertEquals(23.0, booking.getFinalPrice(), "Seats in Col 5 or 6 should get +$3.00 fee");
    }

    @Test
    void testCancelMethod() {

        TwoDMovie movie = new TwoDMovie("Test Movie", 90, 10.0);
        ShowTime show = new ShowTime(movie, LocalDateTime.now(), 5, 6);
        Seat seat = show.getSeats().get(0);
        Customer cust = new Customer("Test", "test@test.com", false, false);

        Booking booking = new Booking(cust, show, seat);
        booking.confirmBooking();


        assertFalse(seat.isAvailable(), "Seat should be busy immediately after booking");


        booking.cancel();

        assertTrue(seat.isAvailable(), "Seat should be free again after cancel()");
    }
}