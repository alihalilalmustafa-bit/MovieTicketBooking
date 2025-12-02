package com.moviebooking.model;

/**
 * Represents a specific seat in the cinema.
 * Implements the Bookable interface to handle booking logic.
 */
public class Seat implements Bookable {

    // Encapsulation: Private attributes
    private char row;       // e.g., 'A', 'B'
    private int number;     // e.g., 1, 2
    private boolean isBooked;

    /**
     * Constructor to initialize the seat.
     * @param row The row letter.
     * @param number The seat number.
     */
    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
        this.isBooked = false; // By default, seat is free
    }

    // Implementing abstract methods from Bookable Interface
    @Override
    public boolean isAvailable() {
        return !isBooked;
    }

    @Override
    public void book() {
        if (isAvailable()) {
            isBooked = true;
            System.out.println("Seat " + row + number + " booked successfully.");
        } else {
            System.out.println("Seat " + row + number + " is already booked.");
        }
    }

    // Getter for seat ID (e.g., "A1")
    public String getSeatId() {
        return "" + row + number;
    }
}