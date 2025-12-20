package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific screening of a movie at a specific time.
 * This class manages the relationship between a movie and its seats.
 */
public class ShowTime {

    // Encapsulation: Private attributes
    private Movie movie;
    private LocalDateTime sessionTime;
    private List<Seat> seats;

    /**
     * Constructs a new ShowTime.
     *
     * @param movie       The movie being shown.
     * @param sessionTime The date and time of the show.
     * @param totalRows   Number of rows in the theater.
     * @param seatsPerRow Number of seats per row.
     */
    public ShowTime(Movie movie, LocalDateTime sessionTime, int totalRows, int seatsPerRow) {
        this.movie = movie;
        this.sessionTime = sessionTime;
        this.seats = new ArrayList<>();
        generateSeats(totalRows, seatsPerRow);
    }

    /**
     * Helper method to generate the seat grid (e.g., A1, A2, B1...).
     * Populates the list of seats based on rows and columns.
     *
     * @param rows        Number of rows.
     * @param seatsPerRow Number of seats in each row.
     */
    private void generateSeats(int rows, int seatsPerRow) {
        for (int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i); // Converts 0 to 'A', 1 to 'B', etc.
            for (int j = 1; j <= seatsPerRow; j++) {
                seats.add(new Seat(rowChar, j));
            }
        }
    }

    // Getters for accessing private fields
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