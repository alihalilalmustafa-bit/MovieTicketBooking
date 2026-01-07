package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Movie avatar = new ThreeDMovie("Avatar 2", 190, 15.0, 5.0);
        ShowTime show1 = new ShowTime(avatar, LocalDateTime.now(), 5, 6);
        ConcessionStand shop = new ConcessionStand();

        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        System.out.println("=== 🎬 MOVIE TICKET SYSTEM (INTERACTIVE) 🎬 ===");


        while (appRunning) {
            System.out.println("\n--------------------------------");
            System.out.println(" 1. View Seat  ");
            System.out.println(" 2. Book a Ticket  ");
            System.out.println(" 3. Exit ");
            System.out.println("--------------------------------");
            System.out.print(">> Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    show1.printSeatMap();
                    break;

                case 2:

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Do you have outside food? (true/false): ");
                    boolean hasOutsideFood = scanner.nextBoolean();
                    scanner.nextLine();

                    Customer cust = new Customer(name, email, false, hasOutsideFood);


                    if (cust.hasOutsideFood()) {
                        System.out.println(" SECURITY: Please throw away your outside food.");
                    } else {
                        System.out.println(" SECURITY: Welcome in.");
                    }

                    show1.printSeatMap();
                    System.out.println("Enter Seat ID : ");
                    String seatInput = scanner.nextLine().toUpperCase();



                    Seat selectedSeat = null;
                    for (Seat s : show1.getSeats()) {
                        if (s.getSeatId().equals(seatInput)) {
                            selectedSeat = s;
                            break;
                        }
                    }


                    if (selectedSeat != null && selectedSeat.isAvailable()) {
                        Booking newBooking = new Booking(cust, show1, selectedSeat);
                        newBooking.confirmBooking();


                        System.out.println("\nDo you want snacks? (1: Yes, 2: No)");
                        int snackChoice = scanner.nextInt();
                        double snackCost = 0.0;

                        if (snackChoice == 1) {
                            shop.showMenu();
                            System.out.print("Enter item number to buy: ");
                            int itemIndex = scanner.nextInt() - 1;
                            snackCost = shop.sellFood(cust, itemIndex);
                        }


                        DataManager.saveTransaction(newBooking, snackCost);
                        System.out.println(" Transaction saved to CSV.");

                    } else {
                        System.out.println("Sorry Seat  already taken");
                    }
                    break;

                case 3:
                    System.out.println("Goodbye");
                    appRunning = false;
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }
}