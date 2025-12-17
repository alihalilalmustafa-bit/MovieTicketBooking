package com.moviebooking.model;

import java.time.LocalDateTime;

/**
 * Main class to run the application Demo (Week 2 Update).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Movie Booking System Demo (Week 2) ===\n");

        // 1. Create Movies (Polymorphism: 2D vs 3D)
        Movie avatar = new ThreeDMovie("Avatar 2", 190, 15.0, 5.0); // 3D Movie
        Movie titanic = new TwoDMovie("Titanic", 195, 10.0);       // 2D Movie (New!)

        // 2. Create ShowTimes
        ShowTime show1 = new ShowTime(avatar, LocalDateTime.now(), 5, 8);
        ShowTime show2 = new ShowTime(titanic, LocalDateTime.now().plusHours(3), 5, 8);

        // 3. Create Customer
        Customer customer = new Customer("Ali Mustafa", "ali@example.com");

        // 4. Test Booking 1: 3D Movie
        System.out.println("--- Booking 1: 3D Movie ---");
        Seat seat1 = show1.getSeats().get(0); // A1
        if (seat1.isAvailable()) {
            Booking booking1 = new Booking(customer, show1, seat1);
            booking1.confirmBooking(); // Should be $20 ($15 + $5)
        }

        // 5. Test Booking 2: 2D Movie (New Test)
        System.out.println("\n--- Booking 2: 2D Movie ---");
        Seat seat2 = show2.getSeats().get(0); // A1
        if (seat2.isAvailable()) {
            Booking booking2 = new Booking(customer, show2, seat2);
            booking2.confirmBooking(); // Should be $10 (Base price only)
        }
    }
}