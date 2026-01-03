package com.moviebooking.model;

import java.util.ArrayList;
import java.util.List;

public class ConcessionStand {
    private List<FoodItem> menu;

    public ConcessionStand() {
        menu = new ArrayList<>();
        menu.add(new FoodItem("Small Popcorn", 5.00));
        menu.add(new FoodItem("Large Popcorn", 8.50));
        menu.add(new FoodItem("Soda", 4.00));
        menu.add(new FoodItem("Nachos", 6.50));
    }

    public void showMenu() {
        System.out.println("--- CONCESSION STAND MENU ---");
        for (int i = 0; i < menu.size(); i++) {
            FoodItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }


    public double sellFood(Customer customer, int choiceIndex) {
        if (choiceIndex >= 0 && choiceIndex < menu.size()) {
            FoodItem item = menu.get(choiceIndex);
            System.out.println( customer.getName() + " bought " + item.getName() + " for $" + item.getPrice());
            return item.getPrice();
        }
        return 0.0;
    }

    public int getMenuSize() { return menu.size(); }
}