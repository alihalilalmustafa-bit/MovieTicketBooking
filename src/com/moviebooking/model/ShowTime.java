package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowTime {
    private Movie movie;
    private LocalDateTime sessionTime;
    private List<Seat> seats;

    public ShowTime(Movie movie, LocalDateTime sessionTime, int totalRows, int seatsPerRow) {
        this.movie = movie;
        this.sessionTime = sessionTime;
        this.seats = new ArrayList<>();
        generateSeats(totalRows, seatsPerRow);
    }

    private void generateSeats(int rows, int seatsPerRow) {
        for (int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i);
            for (int j = 1; j <= seatsPerRow; j++) {
                seats.add(new Seat(rowChar, j));
            }
        }
    }

    public boolean isSoldOut() {
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public Movie getMovie() { return movie; }
    public LocalDateTime getSessionTime() { return sessionTime; }
    public List<Seat> getSeats() { return seats; }


    public void printSeatMap() {
        //System.out.println("\n      [ FRONT ]                            [ BACK ]");
        System.out.println("====================================================");

        char currentRow = ' ';


        int rowCounter = 0;

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            char rowOfSeat = seat.getSeatId().charAt(0);

            // 1. START OF A NEW ROW
            if (rowOfSeat != currentRow) {
                if (currentRow != ' ') System.out.println(); // Finish previous line

                // Print the Screen sidebar
                // We print "SCREEN" on the middle row (Row 'C' which is index 2)
                if (rowCounter == 2) {
                    System.out.print("SCREEN | ");
                } else {
                    System.out.print("       | ");
                }

                currentRow = rowOfSeat;
                rowCounter++;
            }

            // 2. PRINT THE SEAT
            // If taken, print [ XX ] to make it look cleaner
            if (seat.isAvailable()) {
                System.out.print("[" + seat.getSeatId() + "] ");
            } else {
                System.out.print("[ XX ] ");
            }
        }
        System.out.println("\n====================================================");
    }
}