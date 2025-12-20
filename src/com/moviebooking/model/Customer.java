package com.moviebooking.model;

/**
 * Represents a customer booking a ticket.
 * Updated to include student status for discounts.
 */
public class Customer {

    private String name;
    private String email;
    private boolean isStudent; // New field added for Week 3 feature

    public Customer(String name, String email, boolean isStudent) {
        this.name = name;
        this.email = email;
        this.isStudent = isStudent;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isStudent() { return isStudent; }
}