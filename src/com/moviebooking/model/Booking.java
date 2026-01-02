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
        this.finalPrice = calculatePrice(); // <--- Calculate immediately
    }

    // This logic checks if the movie is 3D and adds the extra cost
    private double calculatePrice() {
        // 1. Start with Movie Base Price (e.g., $15.00)
        double price = showTime.getMovie().getBasePrice();

        // 2. Add 3D Fee if applicable
        if (showTime.getMovie() instanceof ThreeDMovie) {
            price += ((ThreeDMovie) showTime.getMovie()).getGlassesPrice();
        }

        // 3. DYNAMIC PRICING (Based on Screen being West/Left)
        // We need to look at the SEAT NUMBER (The "1" in "A1")
        // logic: Remove the first letter ('A') to get the number ("1")
        String seatNumString = seat.getSeatId().substring(1);
        int seatNum = Integer.parseInt(seatNumString);

        if (seatNum <= 2) {
            // Seats 1 and 2 are very close to the screen -> DISCOUNT
            price -= 2.00;
            System.out.println("   (Applied Front Row Discount: -$2.00)");
        }
        else if (seatNum >= 5) {
            // Seats 5 and 6 are far back (VIP) -> EXTRA CHARGE
            price += 3.00;
            System.out.println("   (Applied VIP Back Row Fee: +$3.00)");
        }

        return price;

    }

    public void confirmBooking() {
        if (seat.isAvailable()) {
            seat.setAvailable(false);
            System.out.println("Booking Confirmed for: " + customer.getName());
            // Show the price in the console too
            System.out.println("Movie: " + showTime.getMovie().getTitle() + " | Price: $" + finalPrice);
        } else {
            System.out.println("Booking Failed: Seat is already taken.");
        }
    }
    // --- NEW METHOD: Cancel Logic ---
    public void cancel() {
        // 1. Free the seat so someone else can take it later
        seat.setAvailable(true);
        System.out.println("❌ CANCELLATION PROCESSED: Seat " + seat.getSeatId() + " is now free.");
    }

    // Getters
    public Customer getCustomer() { return customer; }
    public ShowTime getShowTime() { return showTime; }
    public Seat getSeat() { return seat; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public double getFinalPrice() { return finalPrice; } // <--- NEW GETTER
}