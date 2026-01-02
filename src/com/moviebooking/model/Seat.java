package com.moviebooking.model;

public class Seat implements Bookable {

    private char row;
    private int number;
    private boolean isBooked;

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
        this.isBooked = false;
    }

    // Required for your Booking logic
    public void setAvailable(boolean available) {
        this.isBooked = !available;
    }

    @Override
    public boolean isAvailable() {
        return !isBooked;
    }

    // --- FIX IS HERE: Change 'void' to 'boolean' ---
    @Override
    public boolean book() {
        if (isAvailable()) {
            isBooked = true;
            System.out.println("Seat " + row + number + " booked successfully.");
            return true; // Return true because booking succeeded
        } else {
            System.out.println("Seat " + row + number + " is already booked.");
            return false; // Return false because booking failed
        }
    }

    public String getSeatId() {
        return "" + row + number;
    }
}