package com.moviebooking.model;

import java.util.ArrayList;
import java.util.List;


public class ConcessionStand {


    private List<FoodItem> snacks;

    public ConcessionStand() {
        this.snacks = new ArrayList<>();


    }


    public void initializeDefaultMenu() {
        addSnack(new FoodItem("Small Popcorn", 5.00));
        addSnack(new FoodItem("Large Popcorn", 8.50));
        addSnack(new FoodItem("Soda", 4.00));
        addSnack(new FoodItem("Nachos", 6.50));
    }


    public void addSnack(FoodItem item) {
        snacks.add(item);
    }


    public List<FoodItem> getSnacks() {
        return snacks;
    }

    public void showMenu() {
        System.out.println("--- CONCESSION STAND MENU ---");
        for (int i = 0; i < snacks.size(); i++) {
            FoodItem item = snacks.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }

    public double sellFood(Customer customer, int choiceIndex) {
        if (choiceIndex >= 0 && choiceIndex < snacks.size()) {
            FoodItem item = snacks.get(choiceIndex);
            System.out.println(customer.getName() + " bought " + item.getName() + " for $" + item.getPrice());
            return item.getPrice();
        }
        return 0.0;
    }

    public int getMenuSize() {
        return snacks.size();
    }
}