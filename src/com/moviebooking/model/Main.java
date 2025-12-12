package com.moviebooking.model;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // 1. Create Movies (Polymorphism)
        Movie movie = new ThreeDMovie("Avatar 2", 190, 15.0, 5.0);

        // 2. Create ShowTime
        ShowTime showTime = new ShowTime(movie, LocalDateTime.now(), 5, 8);

        // 3. Create Customer
        Customer customer = new Customer("Student Name", "student@arel.edu.tr");

        // 4. Select a Seat (A1)
        Seat seat = showTime.getSeats().get(0);

        // 5. Create Booking
        if (seat.isAvailable()) {
            Booking booking = new Booking(customer, showTime, seat);
            booking.confirmBooking();
        } else {
            System.out.println("Seat is taken.");
        }
    }
}