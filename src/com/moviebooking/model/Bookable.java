package com.moviebooking.model;

public interface Bookable {

    /**
     * Checks if the item (Seat or Showtime) is free.
     * @return true if available, false if taken.
     */
    boolean isAvailable();

    /**
     * Attempts to book the item.
     * @return true if booking succeeded, false if it failed (e.g. already taken).
     */
    boolean book();
}