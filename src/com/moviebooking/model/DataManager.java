package com.moviebooking.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime; // <--- ADDED THIS IMPORT
import java.time.format.DateTimeFormatter;

public class DataManager {

    private static final String FILE_NAME = "bookings.csv";

    // --- METHOD 1: Save Normal Booking ---
    public static void saveTransaction(Booking booking, double snackCost) {

        File file = new File(FILE_NAME);

        // Smart Check: Create header if file is missing or empty
        boolean needsHeader = !file.exists() || file.length() == 0;

        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // 1. Write the Header Row
            if (needsHeader) {
                pw.println("Customer Name, Movie, Seat,  Date,     Ticket Price, Snack Cost, Total Paid");
            }

            // 2. Prepare Data
            String customerName = booking.getCustomer().getName();
            String movieTitle = booking.getShowTime().getMovie().getTitle();
            String seatId = booking.getSeat().getSeatId();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String date = booking.getBookingDate().format(fmt);

            double ticketPrice = booking.getFinalPrice();
            double total = ticketPrice + snackCost;

            // 3. Write Data
            pw.printf("%s, %s, %s, %s, $%.2f, $%.2f, $%.2f%n",
                    customerName, movieTitle, seatId, date, ticketPrice, snackCost, total);

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // --- METHOD 2: Save Refund (NEW) ---
    public static void saveRefund(Booking booking, double snackRefund) {

        File file = new File(FILE_NAME);

        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Prepare Data for the log
            String customerName = booking.getCustomer().getName();
            String movieTitle = booking.getShowTime().getMovie().getTitle();
            String seatId = booking.getSeat().getSeatId();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String date = LocalDateTime.now().format(fmt); // Refund time is NOW

            // CALCULATE NEGATIVE VALUES
            double ticketRefund = -booking.getFinalPrice();
            double snackRef = -snackRefund;
            double totalRefund = ticketRefund + snackRef;

            // Write Refund Row: Adds "(REFUND)" to name and uses negative numbers
            pw.printf("%s (REFUND), %s, %s, %s, $%.2f, $%.2f, $%.2f%n",
                    customerName, movieTitle, seatId, date, ticketRefund, snackRef, totalRefund);

            System.out.println("    [Refund Logged] " + totalRefund);

        } catch (IOException e) {
            System.out.println("Error saving refund: " + e.getMessage());
        }
    }
}