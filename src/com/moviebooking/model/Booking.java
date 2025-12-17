package com.moviebooking.model;

/**
 * Represents a final booking transaction.
 * Links Customer, ShowTime, and Seat together.
 */
public class Booking {

    private Customer customer;
    private ShowTime showTime;
    private Seat seat;
    private double totalPrice;

    public Booking(Customer customer, ShowTime showTime, Seat seat) {
        this.customer = customer;
        this.showTime = showTime;
        this.seat = seat;
        this.totalPrice = calculateTotal();
    }

    /**
     * Calculates the price based on movie type (Polymorphism).
     */
    private double calculateTotal() {
        return showTime.getMovie().calculatePrice();
    }
    /**
     * Confirms the booking, marks the seat as taken,
     * and prints the ticket details to the console.
     */
    public void confirmBooking() {
        seat.book();
        System.out.println("Booking confirmed for " + customer.getName());
        System.out.println("Movie: " + showTime.getMovie().getTitle());
        System.out.println("Seat: " + seat.getSeatId());
        System.out.println("Price: $" + totalPrice);
    }

    // Getters
    public double getTotalPrice() {
        return totalPrice;
    }
}