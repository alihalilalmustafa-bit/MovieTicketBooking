package com.moviebooking.model;

import java.time.LocalDateTime;

public class Booking {
    private Customer customer;
    private ShowTime showTime;
    private Seat seat;
    private LocalDateTime bookingDate;
    private double finalPrice; // <--- NEW FIELD

    public Booking(Customer customer, ShowTime showTime, Seat seat) {
        this.customer = customer;
        this.showTime = showTime;
        this.seat = seat;
        this.bookingDate = LocalDateTime.now();
        this.finalPrice = calculatePrice();
    }


    private double calculatePrice() {

        double price = showTime.getMovie().getBasePrice();


        if (showTime.getMovie() instanceof ThreeDMovie) {
            price += ((ThreeDMovie) showTime.getMovie()).getGlassesPrice();
        }


        String seatNumString = seat.getSeatId().substring(1);
        int seatNum = Integer.parseInt(seatNumString);

        if (seatNum <= 2) {

            price -= 2.00;
            System.out.println("   (Applied Front Row Discount: -$2.00)");
        }
        else if (seatNum >= 5) {

            price += 3.00;
            System.out.println("   (Applied VIP Back Row Fee: +$3.00)");
        }

        return price;

    }

    public void confirmBooking() {
        if (seat.isAvailable()) {
            seat.setAvailable(false);
            System.out.println("Booking Confirmed for: " + customer.getName());

            System.out.println("Movie: " + showTime.getMovie().getTitle() + " | Price: $" + finalPrice);
        } else {
            System.out.println("Booking Failed: Seat is already taken.");
        }
    }

    public void cancel() {

        seat.setAvailable(true);
        System.out.println("❌ CANCELLATION PROCESSED: Seat " + seat.getSeatId() + " is now free.");
    }


    public Customer getCustomer() { return customer; }
    public ShowTime getShowTime() { return showTime; }
    public Seat getSeat() { return seat; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public double getFinalPrice() { return finalPrice; }
}