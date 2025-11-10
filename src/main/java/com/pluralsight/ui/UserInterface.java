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
                    addSandwich(order);
                    break;
                case "2":
                    addDrink(order);
                    break;
                case "3":
                    addChipsFlow(order);
                    break;
                case "4":
                    checkout(order);
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

    private void addSandwich(Order order) {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n==Sandwich==");
            System.out.println("1) Select bread");
            System.out.println("2) Select size");
            System.out.println("3) Meat toppings (allow extras)");
            System.out.println("4) Cheese toppings (allow extras)");
            System.out.println("5) Other regular toppings");
            System.out.println("6) Sauces");
            System.out.println("7) Toasted?");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    selectBread(order);
                    break;
                case "2":
                    selectSize(order);
                    break;
                case "3":
                    meatTopping(order);
                    break;
                case "4":
                    cheeseTopping(order);
                    break;
                case "5":
                    otherTopping(order);
                    break;
                case "6":
                    souces(order);
                    break;
                case "7":
                    toasted(order);
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
    private void addDrink(Order order) {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n==Drink==");
            System.out.println("1) Select Drink Size");
            System.out.println("2) Select Drink Flavour");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    chooseDrinkSize(order);
                    break;
                case "2":
                    chooseDrinkFlavor(order);
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
    private void addChipsFlow(Order order) {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n==Chips==");
            System.out.println("1) Add Chips");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addChipType(order);
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
    private void addChipType(Order order) {
        System.out.println("Add Chips");
    }
    private void chooseDrinkSize(Order order) {
        System.out.println("Choose Drink Size");
    }
    private void chooseDrinkFlavor(Order order) {
        System.out.println("Choose Drink Flavor");
    }

    private void selectBread(Order order){
        System.out.println("Add bread flow");

    }
    private void selectSize(Order order){
        System.out.println("Add size flow");
    }
    private void meatTopping(Order order){
        System.out.println("Add meat topping flow");
    }
    private void cheeseTopping(Order order){
        System.out.println("Add cheese topping flow");
    }
    private void otherTopping(Order order){
        System.out.println("Add other topping flow");
    }
    private void souces(Order order){
        System.out.println("Add souces flow");
    }
    private void toasted(Order order){
        System.out.println("Add toasted option");
    }

    private void checkout(Order order) {
        System.out.println("Checkout flow");

    }

}
