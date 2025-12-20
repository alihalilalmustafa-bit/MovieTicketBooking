package com.moviebooking.model;

import java.time.LocalDateTime;

/**
 * Main class to run the application Demo.
 * Final Version: Testing multiple customers and discount logic.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Movie Booking System Demo (Final) ===\n");

        // 1. Create Movies
        Movie avatar = new ThreeDMovie("Avatar 2", 190, 15.0, 5.0); // 3D ($20)
        Movie titanic = new TwoDMovie("Titanic", 195, 10.0);       // 2D ($10)

        // 2. Create ShowTimes
        ShowTime show1 = new ShowTime(avatar, LocalDateTime.now(), 5, 8);
        ShowTime show2 = new ShowTime(titanic, LocalDateTime.now().plusHours(3), 5, 8);

        // 3. Create Multiple Customers
        Customer ali = new Customer("Ali Mustafa", "alistafa242@gmail.com", false);   // Regular
        Customer ahmed = new Customer("Ahmed student", "ahmed@uni.edu", true);  // Student
        Customer sara = new Customer("Sara Student", "sara@uni.edu", true);     // Student
        Customer john = new Customer("John Regular", "john1564@gmail.com", false);   // Regular

        // 4. Booking Scenario 1: Regular User -> 3D Movie (Full Price)
        System.out.println("--- Booking 1: Ali (Regular) -> Avatar (3D) ---");
        bookTicket(ali, show1, 0); // Seat A1 (Expected: $20.0)

        // 5. Booking Scenario 2: Student User -> 2D Movie (Discount)
        System.out.println("\n--- Booking 2: Ahmed (Student) -> Titanic (2D) ---");
        bookTicket(ahmed, show2, 0); // Seat A1 (Expected: $9.0)

        // 6. Booking Scenario 3: Student User -> 3D Movie (Discount on expensive movie)
        System.out.println("\n--- Booking 3: Sara (Student) -> Avatar (3D) ---");
        bookTicket(sara, show1, 1); // Seat A2 (Expected: $18.0)

        // 7. Booking Scenario 4: Regular User -> 2D Movie (Full Price)
        System.out.println("\n--- Booking 4: John (Regular) -> Titanic (2D) ---");
        bookTicket(john, show2, 1); // Seat A2 (Expected: $10.0)
    }

    // Helper method to make the main code cleaner
    public static void bookTicket(Customer customer, ShowTime show, int seatIndex) {
        Seat seat = show.getSeats().get(seatIndex);
        if (seat.isAvailable()) {
            Booking booking = new Booking(customer, show, seat);
            booking.confirmBooking();
        } else {
            System.out.println("Seat is already taken!");
        }
    }
}