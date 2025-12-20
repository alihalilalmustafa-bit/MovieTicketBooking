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

        // Feature: Student Discount (10% OFF)
        if (customer.isStudent()) {
            price = price * 0.90;
            System.out.println("(Student Discount Applied: 10%)");
        }
        return price;
    }

    /**
     * Confirms the booking and prints the receipt.
     */
    public void confirmBooking() {
        seat.book();
        printReceipt(); // استدعاء دالة الطباعة الجديدة
    }

    /**
     * Helper method to print ticket details cleanly.
     */
    private void printReceipt() {
        System.out.println("\n--- TICKET RECEIPT ---");
        System.out.println("Status: Booking Confirmed!");
        System.out.println("User:   " + customer.getName());
        System.out.println("Movie:  " + showTime.getMovie().getTitle());
        System.out.println("Seat:   " + seat.getSeatId());
        System.out.println("Price:  $" + totalPrice);
        System.out.println("----------------------\n");
    }
}