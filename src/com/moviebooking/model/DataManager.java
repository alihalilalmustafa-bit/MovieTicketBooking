package com.moviebooking.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Manages data persistence for the application.
 * Responsible for saving booking records to a CSV file.
 */
public class DataManager {

    // The name of the file where data will be stored
    private static final String FILE_NAME = "booking_history.csv";

    /**
     * Saves a single booking record to the CSV file.
     * The file is opened in 'append' mode so previous data is not lost.
     * * @param booking The booking object containing all details.
     */
    public static void saveBooking(Booking booking) {
        // Try-with-resources ensures the file is closed automatically after writing
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // 1. Extract Data from the Booking object
            String userID = booking.getCustomer().getEmail(); // Unique ID (Email)
            String userName = booking.getCustomer().getName();
            String userType = booking.getCustomer().isStudent() ? "Student" : "Regular";
            String movieTitle = booking.getShowTime().getMovie().getTitle();
            String seatNumber = booking.getSeat().getSeatId();
            double pricePaid = booking.getTotalPrice();
            String bookingDate = LocalDateTime.now().toString();
            String status = "CONFIRMED";

            // 2. Format as CSV (Comma Separated Values)
            // Format: ID, Name, Type, Movie, Seat, Price, Status, Date
            pw.printf("%s,%s,%s,%s,%s,%.2f,%s,%s%n",
                    userID, userName, userType, movieTitle, seatNumber, pricePaid, status, bookingDate);

            System.out.println(">> [System]: Booking saved to " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}