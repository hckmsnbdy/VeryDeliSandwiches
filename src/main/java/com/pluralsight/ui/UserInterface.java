package com.pluralsight.ui;

import com.pluralsight.models.Order;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }

    // Home Screen
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("VERY DELI SANDWICHES");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    startOrder();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // Order Menu
    private void startOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nNEW ORDER");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addSandwichFlow(order);
                    break;
                case "2":
                    addDrinkFlow(order);
                    break;
                case "3":
                    addChipsFlow(order);
                    break;
                case "4":
                    checkoutFlow(order);
                    ordering = false;
                    break;
                case "0":
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }



    private void addSandwichFlow(Order order) {
        System.out.println("Add Sandwich flow");

    }

    private void addDrinkFlow(Order order) {
        System.out.println("Add Drink flow");

    }

    private void addChipsFlow(Order order) {
        System.out.println("Add Chips flow");

    }

    private void checkoutFlow(Order order) {
        System.out.println("Checkout flow");

    }
}
