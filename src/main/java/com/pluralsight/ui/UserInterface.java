package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.services.PriceService;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final PriceService priceService = new PriceService();

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
            System.out.println("1) Select your bread:");
            System.out.println("2) Sandwich size:");
            System.out.println("3) Toppings:");
            System.out.println("4) Select Sauces:");
            System.out.println("5) Would you like the sandwich toasted?");
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
                    selectTopping(order);
                    break;
                case "4":
                    sauces(order);
                case "5":
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
    private void selectTopping(Order order){
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nToppings");
            System.out.println("1) Meat:");
            System.out.println("2) Cheese:");
            System.out.println("3) Other toppings");
            System.out.println("4) Sauces");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    meatTopping(order);
                    break;
                case "2":
                    cheeseTopping(order);
                    break;
                case "3":
                    otherTopping(order);
                    break;
                case "4":
                    sauces(order);
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
            System.out.println("\nDrink");
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
            System.out.println("\nChips");
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
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nBreads");
            System.out.println("1) White:");
            System.out.println("2) Wheat:");
            System.out.println("3) Rye:");
            System.out.println("4) Wrap:");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //whiteBread(order);
                    break;
                case "2":
                    //wheat(order);
                    break;
                case "3":
                    //rye(order);
                    break;
                case "4":
                    //wrap(order);
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
    private void selectSize(Order order){
        System.out.println("Add size flow");
    }
    private void meatTopping(Order order){
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nMeats");
            System.out.println("1) Steak:");
            System.out.println("2) Ham:");
            System.out.println("3) Salami:");
            System.out.println("4) Roast beef:");
            System.out.println("5) Chicken:");
            System.out.println("6) Bacon:");
            System.out.println("7) Extra Meat:");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //steak(order);
                    break;
                case "2":
                    //ham(order);
                    break;
                case "3":
                    //salami(order);
                    break;
                case "4":
                    //roastBeef(order);
                    break;
                case "5":
                    //chicken(order);
                    break;
                case "6":
                    //bacon(order);
                    break;
                case "7":
                    //extraMeat(order);
                    break;
                case "0":
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }    }
    private void cheeseTopping(Order order){
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nCheese");
            System.out.println("1) American:");
            System.out.println("2) Provolone:");
            System.out.println("3) Cheddar:");
            System.out.println("4) Swiss:");
            System.out.println("5) Extra Cheese:");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //american(order);
                    break;
                case "2":
                    //provolone(order);
                    break;
                case "3":
                    //cheddar(order);
                    break;
                case "4":
                    //swiss(order);
                    break;
                case "5":
                    //extraCheese(order);
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
    private void otherTopping(Order order){
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nOther Toppings");
            System.out.println("1) Lettuce:");
            System.out.println("2) Peppers:");
            System.out.println("3) Onions:");
            System.out.println("4) Tomatoes:");
            System.out.println("5) Jalapeños:");
            System.out.println("6) Cucumbers:");
            System.out.println("7) Pickles:");
            System.out.println("8) Guacamole:");
            System.out.println("9) Mushrooms:");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //lettuce(order);
                    break;
                case "2":
                    //peppers(order);
                    break;
                case "3":
                    //onions(order);
                    break;
                case "4":
                    //tomatoes(order);
                    break;
                case "5":
                    //jalapeños(order);
                    break;
                case "6":
                    //cucumbers(order);
                    break;
                case "7":
                    //pickles(order);
                    break;
                case "8":
                    //guacamole(order);
                    break;
                case "9":
                    //mushrooms(order);
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
    private void sauces(Order order){
        boolean ordering = true;

        while (ordering) {
            System.out.println("\nSauces");
            System.out.println("1) Mayo:");
            System.out.println("2) Mustard:");
            System.out.println("3) Ketchup:");
            System.out.println("4) Ranch:");
            System.out.println("5) Thousand Islands:");
            System.out.println("6) Vinaigrette:");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //mayo(order);
                    break;
                case "2":
                    //mustard(order);
                    break;
                case "3":
                    //ketchup(order);
                    break;
                case "4":
                    //ranch(order);
                    break;
                case "5":
                    //thousandIslands(order);
                    break;
                case "6":
                    //vinaigrette(order);
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
    private void toasted(Order order){
        System.out.println("Add toasted option");
    }

    private void checkout(Order order) {
        if (order.getSandwiches().isEmpty() &&
                order.getDrinks().isEmpty() &&
                order.getChips().isEmpty()) {
            System.out.println("\n==Checkout==");
            System.out.println("Order is empty. Please add at least one item.");
            return;
        }

        boolean ordering = true;

        while (ordering) {
            System.out.println("\nCheckout");
            System.out.println(order.toString());

            double total = order.calculateTotal(priceService);
            System.out.printf("TOTAL: $%.2f%n", total);

            System.out.println("1) Confirm");
            System.out.println("0) Cancel Order");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            if ("1".equals(input)) {
                // Receipt
                System.out.println("Order confirmed. (Receipt????)");
            } else {
                System.out.println("Checkout cancelled.");
            }
        }

    }
    private void confirmOrder(Order order){
        System.out.println("Create Receipt");
    }

}
