package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConcessionStandTest {

    @Test
    void testAddSnack() {
        // Method 1: Test adding a single item
        ConcessionStand stand = new ConcessionStand();
        FoodItem popcorn = new FoodItem("Popcorn", 5.00);

        stand.addSnack(popcorn);

        List<FoodItem> snacks = stand.getSnacks();
        assertEquals(1, snacks.size(), "Snack list should have 1 item");
        assertEquals("Popcorn", snacks.get(0).getName(), "Item name should match");
    }

    @Test
    void testMultipleSnacks() {

        ConcessionStand stand = new ConcessionStand();
        stand.addSnack(new FoodItem("Soda", 2.50));
        stand.addSnack(new FoodItem("Candy", 1.50));

        assertEquals(2, stand.getSnacks().size(), "Snack list should have 2 items");
    }
}