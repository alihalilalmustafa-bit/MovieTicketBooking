package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CINEMA SIMULATION (With Clean Data) ===\n");

        Movie avatar = new ThreeDMovie("Avatar 2", 190, 15.0, 5.0);
        ShowTime show1 = new ShowTime(avatar, LocalDateTime.now(), 5, 6);
        ConcessionStand shop = new ConcessionStand();

        int numberOfCustomers = 15;
        Random random = new Random();
        String[] possiblePreferences = {"MIDDLE", "MIDDLE", "BACK", "BACK", "FRONT"};

        for (int i = 1; i <= numberOfCustomers; i++) {
            String name = "Customer " + i;
            Customer cust = new Customer(name, "email" + i + "@test.com", false, random.nextBoolean());
            String preference = possiblePreferences[random.nextInt(possiblePreferences.length)];

            System.out.println("\n " + name + "  (Wants " + preference + ") ");

            // --- SECURITY ---
            if (cust.hasOutsideFood()) {
                System.out.println("👮 SECURITY: Please discard outside food.");
            } else {
                System.out.println(" SECURITY: Welcome.");
            }

            // --- FIND SEAT ---
            int chosenIndex = findBestSeatForPreference(show1, preference);

            // (Fallback logic)
            if (chosenIndex == -1) chosenIndex = findBestSeatForPreference(show1, "MIDDLE");
            if (chosenIndex == -1) chosenIndex = findBestSeatForPreference(show1, "BACK");
            if (chosenIndex == -1) chosenIndex = findBestSeatForPreference(show1, "FRONT");

            // --- BOOKING & SNACKS ---
            if (chosenIndex != -1) {
                // 1. Process Booking (Create object, but don't save to CSV yet)
                Booking currentBooking = processBooking(cust, show1, chosenIndex);
                System.out.println(" SUCCESS: Seat " + show1.getSeats().get(chosenIndex).getSeatId() + " booked.");

                // 2. Buy Snacks
                double snackPrice = 0.0;
                int chanceToBuy = cust.hasOutsideFood() ? 90 : 50;

                if (random.nextInt(100) < chanceToBuy) {
                    System.out.println("🍿 SNACKS: Going to shop...");
                    int foodChoice = random.nextInt(shop.getMenuSize());

                    // THIS NOW RETURNS THE PRICE
                    snackPrice = shop.sellFood(cust, foodChoice);
                }

                // 3. Save EVERYTHING (Ticket + Snacks)
                DataManager.saveTransaction(currentBooking, snackPrice);
                // --- PHASE 5: RANDOM CANCELLATION ---
                // 15% chance the customer cancels immediately
                if (random.nextInt(100) < 15) {

                    // 1. Pick a random excuse
                    String[] excuses = {
                            "My cat is sick 😿",
                            "Traffic is terrible 🚗",
                            "I forgot I have a date 💔",
                            "I'm not feeling well 🤢",
                            "My boss called me into work 💼"
                    };
                    String randomReason = excuses[random.nextInt(excuses.length)];

                    System.out.println("\n📞 RINNNG! " + name + " is calling to cancel...");
                    System.out.println("   (Reason: '" + randomReason + "')");

                    // 2. Cancel and Refund
                    currentBooking.cancel();
                    DataManager.saveRefund(currentBooking, snackPrice);
                }

            } else {
                System.out.println("⛔ FAIL: Theater Full.");
            }
        } // <--- End of Customer Loop

        // --- NEW ADDITION: SHOW THE SCREEN AND MAP ---
        System.out.println("\n\n=================================");
        System.out.println("      🎥  SCREEN THIS WAY  🎥      ");
        System.out.println("=================================");
        show1.printSeatMap();


        // --- PHASE 7: FINANCIAL SUMMARY ---
        System.out.println("\n--- 💰 DAILY FINANCIAL REPORT 💰 ---");
        // Note: In a real app, you would calculate these variables inside the loop
        // But for now, we can just look at the CSV file or just end the simulation.
        System.out.println("Transactions saved to: bookings.csv");
        System.out.println("Status: System Shutting Down...");
    }

    // --- HELPERS ---

    public static Booking processBooking(Customer customer, ShowTime show, int seatIndex) {
        Seat seat = show.getSeats().get(seatIndex);
        if (seat.isAvailable()) {
            Booking booking = new Booking(customer, show, seat);
            booking.confirmBooking();
            return booking;
        }
        return null;
    }

    // --- UPDATED HELPER: Strict Column Logic (1=Front, 6=Back) ---
    public static int findBestSeatForPreference(ShowTime show, String preference) {
        List<Seat> seats = show.getSeats();

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);

            // Skip if taken
            if (!seat.isAvailable()) continue;

            // Get the Column Number (e.g., "A6" -> 6)
            int col = Integer.parseInt(seat.getSeatId().substring(1));

            // STRICT MATCHING
            if (preference.equals("FRONT")) {
                // Only Column 1 is Front
                if (col == 1) return i;
            }
            else if (preference.equals("BACK")) {
                // Only Column 6 is Back (VIP)
                if (col == 6) return i;
            }
            else if (preference.equals("MIDDLE")) {
                // Columns 2, 3, 4, 5 are Middle
                if (col >= 2 && col <= 5) return i;
            }
        }
        return -1; // Specific section is full
    }
}