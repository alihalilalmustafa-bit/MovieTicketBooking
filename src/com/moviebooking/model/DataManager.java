package com.moviebooking.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataManager {

    private static final String FILE_NAME = "bookings.csv";


    public static void saveTransaction(Booking booking, double snackCost) {

        File file = new File(FILE_NAME);


        boolean needsHeader = !file.exists() || file.length() == 0;

        try (FileWriter fw = new FileWriter(file,true);
             PrintWriter pw = new PrintWriter(fw)) {


            if (needsHeader) {
                pw.println("Customer Name, Movie, Seat,  Date,     Ticket Price, Snack Cost, Total Paid");
            }


            String customerName = booking.getCustomer().getName();
            String movieTitle = booking.getShowTime().getMovie().getTitle();
            String seatId = booking.getSeat().getSeatId();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String date = booking.getBookingDate().format(fmt);

            double ticketPrice = booking.getFinalPrice();
            double total = ticketPrice + snackCost;


            pw.printf("%s, %s, %s, %s, $%.2f, $%.2f, $%.2f%n",
                    customerName, movieTitle, seatId, date, ticketPrice, snackCost, total);

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    public static void saveRefund(Booking booking, double snackRefund) {

        File file = new File(FILE_NAME);

        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {


            String customerName = booking.getCustomer().getName();
            String movieTitle = booking.getShowTime().getMovie().getTitle();
            String seatId = booking.getSeat().getSeatId();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String date = LocalDateTime.now().format(fmt); // Refund time is NOW


            double ticketRefund = -booking.getFinalPrice();
            double snackRef = -snackRefund;
            double totalRefund = ticketRefund + snackRef;


            pw.printf("%s (REFUND), %s, %s, %s, $%.2f, $%.2f, $%.2f%n",
                    customerName, movieTitle, seatId, date, ticketRefund, snackRef, totalRefund);

            System.out.println("    [Refund Logged] " + totalRefund);

        } catch (IOException e) {
            System.out.println("Error saving refund: " + e.getMessage());
        }
    }
}