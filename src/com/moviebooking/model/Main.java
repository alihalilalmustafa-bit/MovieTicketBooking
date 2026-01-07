package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        List<ShowTime> actionMovies = new ArrayList<>();
        actionMovies.add(new ShowTime(new ThreeDMovie("Avatar 2", 190, 15.0, 5.0), LocalDateTime.now(), 5, 6));
        actionMovies.add(new ShowTime(new ThreeDMovie("John Wick 4", 170, 15.0, 5.0), LocalDateTime.now(), 5, 6));

        List<ShowTime> horrorMovies = new ArrayList<>();
        horrorMovies.add(new ShowTime(new TwoDMovie("The Nun 2", 110, 12.0), LocalDateTime.now(), 5, 6));
        horrorMovies.add(new ShowTime(new TwoDMovie("Scream 6", 120, 12.0), LocalDateTime.now(), 5, 6));
        horrorMovies.add(new ShowTime(new TwoDMovie("It Chapter Two", 160, 12.0), LocalDateTime.now(), 5, 6));

        List<ShowTime> comedyMovies = new ArrayList<>();
        comedyMovies.add(new ShowTime(new TwoDMovie("Minions", 95, 10.0), LocalDateTime.now(), 5, 6));
        comedyMovies.add(new ShowTime(new TwoDMovie("Jumanji", 110, 10.0), LocalDateTime.now(), 5, 6));

        ConcessionStand shop = new ConcessionStand();
        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        System.out.println("=== 🎬 MEGA PLEX CINEMA SYSTEM 🎬 ===");

        while (appRunning) {
            System.out.println("\n--------------------------------");
            System.out.println(" 1. Book a Ticket ");
            System.out.println(" 2. Exit ");
            System.out.println("--------------------------------");
            System.out.print(">> Choose option: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 1) {
                System.out.println("\n--- SELECT GENRE ---");
                System.out.println("1. Action 💥");
                System.out.println("2. Horror 👻");
                System.out.println("3. Comedy 😂");
                System.out.print("Enter Genre Number: ");

                int genreChoice = scanner.nextInt();
                scanner.nextLine();

                List<ShowTime> selectedList = null;

                if (genreChoice == 1) {
                    selectedList = actionMovies;
                } else if (genreChoice == 2) {
                    selectedList = horrorMovies;
                } else if (genreChoice == 3) {
                    selectedList = comedyMovies;
                } else {
                    System.out.println(" Invalid Genre!");
                    continue;
                }

                System.out.println("\n--- AVAILABLE MOVIES ---");
                for (int i = 0; i < selectedList.size(); i++) {
                    System.out.println((i + 1) + ". " + selectedList.get(i).getMovie().getTitle());
                }

                System.out.print("Select Movie Number: ");
                int movieIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                if (movieIndex < 0 || movieIndex >= selectedList.size()) {
                    System.out.println(" Invalid Movie Selection!");
                    continue;
                }


                ShowTime selectedShow = selectedList.get(movieIndex);
                System.out.println(" You selected: " + selectedShow.getMovie().getTitle());


                System.out.print("Enter  Youre Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Youre Email: ");
                String email = scanner.nextLine();

                System.out.print("Outside food? (true/false): ");
                boolean hasOutsideFood = scanner.nextBoolean();
                scanner.nextLine();

                Customer cust = new Customer(name, email, false, hasOutsideFood);

                if (cust.hasOutsideFood()) System.out.println(" Security: Outside food removed.");
                else System.out.println(" Security: Welcome.");


                selectedShow.printSeatMap();
                System.out.print("Enter Seat ID (e.g. A1): ");
                String seatInput = scanner.nextLine().toUpperCase();

                Seat targetSeat = null;
                for (Seat s : selectedShow.getSeats()) {
                    if (s.getSeatId().equals(seatInput)) {
                        targetSeat = s;
                        break;
                    }
                }

                if (targetSeat != null && targetSeat.isAvailable()) {
                    Booking booking = new Booking(cust, selectedShow, targetSeat);
                    booking.confirmBooking();

                    // Snacks
                    System.out.print("Buy snacks? (1-Yes, 0-No): ");
                    int wantSnacks = scanner.nextInt();
                    double snackCost = 0;
                    if (wantSnacks == 1) {
                        shop.showMenu();
                        System.out.print("Choice: ");
                        int sIndex = scanner.nextInt() - 1;
                        snackCost = shop.sellFood(cust, sIndex);
                    }

                    DataManager.saveTransaction(booking, snackCost);
                    System.out.println(" Booking Saved!");
                } else {
                    System.out.println("⛔ Seat Unavailable!");
                }

            } else if (mainChoice == 2) {
                appRunning = false;
                System.out.println("Bye!");
            }
        }
        scanner.close();
    }
}