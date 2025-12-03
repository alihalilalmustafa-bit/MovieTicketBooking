package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ShowTime {

    // Encapsulation: Private attributes
    private Movie movie;
    private LocalDateTime sessionTime;
    private List<Seat> seats;

    /**
     * Constructor
     * @param movie The movie being shown.
     * @param sessionTime The date and time of the show.
     * @param totalRows Number of rows in the theater.
     * @param seatsPerRow Number of seats per row.
     */
    public ShowTime(Movie movie, LocalDateTime sessionTime, int totalRows, int seatsPerRow) {
        this.movie = movie;
        this.sessionTime = sessionTime;
        this.seats = new ArrayList<>();
        generateSeats(totalRows, seatsPerRow);
    }


    private void generateSeats(int rows, int seatsPerRow) {
        for (int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i); // Converts 0 to 'A', 1 to 'B', etc.
            for (int j = 1; j <= seatsPerRow; j++) {
                seats.add(new Seat(rowChar, j));
            }
        }
    }

    // Getters
    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}