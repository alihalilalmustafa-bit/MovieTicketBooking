package com.moviebooking.model;

public class Customer {
    private String name;
    private String email;
    private boolean isVip;
    private boolean hasOutsideFood;

    public Customer(String name, String email, boolean isVip, boolean hasOutsideFood) {
        this.name = name;
        this.email = email;
        this.isVip = isVip;
        this.hasOutsideFood = hasOutsideFood;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isVip() { return isVip; }
    public boolean hasOutsideFood() { return hasOutsideFood; } // Getter
}