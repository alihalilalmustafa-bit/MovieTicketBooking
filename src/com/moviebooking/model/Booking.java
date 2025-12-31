package com.moviebooking.model;

/**
 * Represents a final booking transaction.
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

    private double calculateTotal() {
        double price = showTime.getMovie().calculatePrice();
        if (customer.isStudent()) {
            price = price * 0.90;
        }
        return price;
    }

    public void confirmBooking() {
        seat.book();
        // We will print the receipt via Main or DataManager now
    }

    // Getters needed for CSV saving
    public Customer getCustomer() { return customer; }
    public ShowTime getShowTime() { return showTime; }
    public Seat getSeat() { return seat; }
    public double getTotalPrice() { return totalPrice; }
}