package com.pluralsight.ui;

import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
import com.pluralsight.services.ConsolePrinter;
import com.pluralsight.services.InterfacePrint;
import com.pluralsight.services.PriceService;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Chips;
import com.pluralsight.models.BreadType;
import com.pluralsight.models.SandwichSize;



import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final PriceService priceService = new PriceService();
    private String pendingDrinkSize;
    private String pendingDrinkFlavor;


    public static void main(String[] args) {
        InterfacePrint printer = new ConsolePrinter();
        printer.printMessage("Starting Very Deli Sandwiches...");

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
                    addChipType(order);
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
        // Start with a default sandwich and let the user customize it
        Sandwich sandwich = new Sandwich();

        boolean editing = true;

        while (editing) {
            printCurrentSandwich(sandwich, order);

            System.out.println("1) Select bread");
            System.out.println("2) Select size");
            System.out.println("3) Toppings");
            System.out.println("4) Sauces");
            System.out.println("5) Toasted");
            System.out.println("6) Save sandwich and return to order menu");
            System.out.println("0) Discard sandwich and return to order menu");
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    selectBread(sandwich);
                    break;
                case "2":
                    selectSize(sandwich);
                    break;
                case "3":
                    selectTopping(sandwich);
                    break;
                case "4":
                    sauces(sandwich);
                    break;
                case "5":
                    toasted(sandwich);
                    break;
                case "6":
                    order.addSandwich(sandwich);
                    System.out.println("Sandwich added to order.");
                    editing = false;
                    break;
                case "0":
                    // If sandwich is EMPTY, just cancel
                    boolean isDefault =
                            SandwichSize.FOUR_INCH.equals(sandwich.getSize()) &&
                                    BreadType.WHITE.equals(sandwich.getBread()) &&
                                    !sandwich.isToasted() &&
                                    sandwich.getToppings().isEmpty();

                    if (isDefault) {
                        System.out.println("Sandwich cancelled.");
                        editing = false;
                    } else {
                        // really want to discard the current sandwich?
                        System.out.print("You have a sandwich in progress. Discard it (y/n): ");
                        String confirm = scanner.nextLine().trim().toLowerCase();

                        if (confirm.startsWith("y")) {
                            System.out.println("Sandwich cancelled.");
                            editing = false;
                        } else {
                            System.out.println("Keeping current sandwich.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
    // Prints current sandwich details and price information.
    private void printCurrentSandwich(Sandwich sandwich, Order order) {
        PriceService priceService = new PriceService();

        double sandwichPrice = sandwich.calculatePrice(priceService);
        double currentOrderTotal = order.calculateTotal(priceService);
        double projectedTotal = currentOrderTotal + sandwichPrice;

        List<String> meats = new ArrayList<>();
        List<String> cheeses = new ArrayList<>();
        List<String> others = new ArrayList<>();
        List<String> sauces = new ArrayList<>();

        for (String t : sandwich.getToppings()) {
            if (t.startsWith("Meat: ")) {
                meats.add(t.substring("Meat: ".length()));
            } else if (t.startsWith("Cheese: ")) {
                cheeses.add(t.substring("Cheese: ".length()));
            } else if (t.startsWith("Sauce: ")) {
                sauces.add(t.substring("Sauce: ".length()));
            } else {
                others.add(t);
            }
        }
        addSpacing();
        System.out.println();
        System.out.println("Sandwich Builder");
        System.out.println();
        System.out.println("Current sandwich:");
        System.out.println("  Size: " + sandwich.getSize() + " inch");
        System.out.println("  Bread: " + sandwich.getBread());
        System.out.println("  Toasted: " + (sandwich.isToasted() ? "yes" : "no"));
        System.out.println("  Meats: " + (meats.isEmpty() ? "none" : String.join(", ", meats)));
        System.out.println("  Cheeses: " + (cheeses.isEmpty() ? "none" : String.join(", ", cheeses)));
        System.out.println("  Other toppings: " + (others.isEmpty() ? "none" : String.join(", ", others)));
        System.out.println("  Sauces: " + (sauces.isEmpty() ? "none" : String.join(", ", sauces)));

        System.out.printf("  Sandwich price: $%.2f%n", sandwichPrice);
        System.out.printf("  Current order total (without this sandwich): $%.2f%n", currentOrderTotal);
        System.out.printf("  Projected total with this sandwich: $%.2f%n", projectedTotal);
        System.out.println();
    }


    private String formatSandwichForOutput(Sandwich sandwich) {
        List<String> meats = new ArrayList<>();
        List<String> cheeses = new ArrayList<>();
        List<String> sauces = new ArrayList<>();
        List<String> others = new ArrayList<>();

        for (String t : sandwich.getToppings()) {
            String lower = t.toLowerCase();

            if (lower.startsWith("meat:")) {
                meats.add(t.substring("Meat: ".length()));
            } else if (lower.startsWith("cheese:")) {
                cheeses.add(t.substring("Cheese: ".length()));
            } else if (lower.startsWith("sauce:")) {
                sauces.add(t.substring("Sauce: ".length()));
            } else {
                others.add(t);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("  Size: ").append(sandwich.getSize()).append(" inch\n");
        sb.append("  Bread: ").append(sandwich.getBread()).append("\n");
        sb.append("  Toasted: ").append(sandwich.isToasted() ? "yes" : "no").append("\n");
        sb.append("  Meats: ").append(meats.isEmpty() ? "none" : String.join(", ", meats)).append("\n");
        sb.append("  Cheeses: ").append(cheeses.isEmpty() ? "none" : String.join(", ", cheeses)).append("\n");
        sb.append("  Other toppings: ").append(others.isEmpty() ? "none" : String.join(", ", others)).append("\n");
        sb.append("  Sauces: ").append(sauces.isEmpty() ? "none" : String.join(", ", sauces)).append("\n");
        return sb.toString();
    }


    private void selectTopping(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nToppings");
            System.out.println("1) Meat");
            System.out.println("2) Cheese");
            System.out.println("3) Other toppings");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    meatTopping(sandwich);
                    break;
                case "2":
                    cheeseTopping(sandwich);
                    break;
                case "3":
                    otherTopping(sandwich);
                    break;
                case "0":
                    choosing = false;
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
            System.out.println("0) Back");
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
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }

            if (pendingDrinkSize != null && pendingDrinkFlavor != null) {
                Drink d = new Drink(pendingDrinkSize, pendingDrinkFlavor);
                order.addDrink(d);

                System.out.println("Added drink: " + d.getSize() + " " + d.getFlavor());

                pendingDrinkSize = null;
                pendingDrinkFlavor = null;

                ordering = false;
            }
        }
    }


    private void addChipType(Order order) {

        PriceService price = new PriceService();   // to show prices

        boolean choosing = true;

        while (choosing) {
            System.out.println();
            System.out.println("Choose Chip Type");
            System.out.println("1) Classic        - $" + price.chipsPrice());
            System.out.println("2) BBQ            - $" + price.chipsPrice());
            System.out.println("3) Sour Cream     - $" + price.chipsPrice());
            System.out.println("4) Salt & Vinegar - $" + price.chipsPrice());
            System.out.println("5) Jalapeño       - $" + price.chipsPrice());
            System.out.println("0) Back to order menu");
            System.out.print("Choose: ");

            String input = scanner.nextLine();
            String type;

            switch (input) {
                case "1": type = "Classic"; break;
                case "2": type = "BBQ"; break;
                case "3": type = "Sour Cream"; break;
                case "4": type = "Salt & Vinegar"; break;
                case "5": type = "Jalapeño"; break;
                case "0":
                    System.out.println("Returning to order menu.");
                    return;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            Chips chips = new Chips(type);
            order.addChips(chips);

            System.out.println("Added chips: " + type + " ($" + price.chipsPrice() + ")");
            choosing = false;
        }
    }



    private void chooseDrinkSize(Order order) {
        PriceService price = new PriceService();

        System.out.println();
        System.out.println("Select size: ");
        System.out.println("1) Small  - $" + price.drinkPrice("small"));
        System.out.println("2) Medium - $" + price.drinkPrice("medium"));
        System.out.println("3) Large  - $" + price.drinkPrice("large"));
        System.out.print("Choose: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                pendingDrinkSize = "small";
                System.out.println("Size selected: small");
                break;
            case "2":
                pendingDrinkSize = "medium";
                System.out.println("Size selected: medium");
                break;
            case "3":
                pendingDrinkSize = "large";
                System.out.println("Size selected: large");
                break;
            default:
                System.out.println("Invalid selection.");
                pendingDrinkSize = null;
        }
    }

    private void chooseDrinkFlavor(Order order) {
        System.out.println();
        System.out.println("Select flavor: 1) Cola  2) Orange  3) Lemonade");
        System.out.print("Choose: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                pendingDrinkFlavor = "cola";
                break;
            case "2":
                pendingDrinkFlavor = "orange";
                break;
            case "3":
                pendingDrinkFlavor = "lemonade";
                break;
            default:
                System.out.println("Invalid flavor choice.");
                pendingDrinkFlavor = null;
                return;
        }

        System.out.println("Flavor selected: " + pendingDrinkFlavor);
    }

    private void selectBread(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nBreads ");
            System.out.println("1) White");
            System.out.println("2) Wheat");
            System.out.println("3) Rye");
            System.out.println("4) Wrap");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.setBread(BreadType.WHITE);
                    System.out.println("Bread set to White.");
                    choosing = false;
                    break;
                case "2":
                    sandwich.setBread(BreadType.WHEAT);
                    System.out.println("Bread set to Wheat.");
                    choosing = false;
                    break;
                case "3":
                    sandwich.setBread(BreadType.RYE);
                    System.out.println("Bread set to Rye.");
                    choosing = false;
                    break;
                case "4":
                    sandwich.setBread(BreadType.WRAP);
                    System.out.println("Bread set to Wrap.");
                    choosing = false;
                    break;
                case "0":
                    choosing = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private void selectSize(Sandwich sandwich) {
        PriceService priceService = new PriceService();

        boolean choosing = true;

        while (choosing) {
            System.out.println("\nSize");
            System.out.println("1) 4 inch  - $" + priceService.priceOfBread(SandwichSize.FOUR_INCH));
            System.out.println("2) 8 inch  - $" + priceService.priceOfBread(SandwichSize.EIGHT_INCH));
            System.out.println("3) 12 inch - $" + priceService.priceOfBread(SandwichSize.TWELVE_INCH));
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.setSize(SandwichSize.FOUR_INCH);
                    System.out.println("Size set to 4 inch.");
                    choosing = false;
                    break;
                case "2":
                    sandwich.setSize(SandwichSize.EIGHT_INCH);
                    System.out.println("Size set to 8 inch.");
                    choosing = false;
                    break;
                case "3":
                    sandwich.setSize(SandwichSize.TWELVE_INCH);
                    System.out.println("Size set to 12 inch.");
                    choosing = false;
                    break;
                case "0":
                    choosing = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private void meatTopping(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nMeats");
            System.out.println("1) Steak");
            System.out.println("2) Ham");
            System.out.println("3) Salami");
            System.out.println("4) Roast beef");
            System.out.println("5) Chicken");
            System.out.println("6) Bacon");
            System.out.println("7) Extra Meat");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.addTopping("Meat: Steak");
                    break;
                case "2":
                    sandwich.addTopping("Meat: Ham");
                    break;
                case "3":
                    sandwich.addTopping("Meat: Salami");
                    break;
                case "4":
                    sandwich.addTopping("Meat: Roast beef");
                    break;
                case "5":
                    sandwich.addTopping("Meat: Chicken");
                    break;
                case "6":
                    sandwich.addTopping("Meat: Bacon");
                    break;
                case "7":
                    sandwich.addTopping("Extra Meat");
                    break;
                case "0":
                    choosing = false;
                    continue;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            System.out.println("Meat topping added.");
        }
    }

    private void cheeseTopping(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nCheese");
            System.out.println("1) American");
            System.out.println("2) Provolone");
            System.out.println("3) Cheddar");
            System.out.println("4) Swiss");
            System.out.println("5) Extra Cheese");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.addTopping("Cheese: American");
                    break;
                case "2":
                    sandwich.addTopping("Cheese: Provolone");
                    break;
                case "3":
                    sandwich.addTopping("Cheese: Cheddar");
                    break;
                case "4":
                    sandwich.addTopping("Cheese: Swiss");
                    break;
                case "5":
                    sandwich.addTopping("Extra Cheese");
                    break;
                case "0":
                    choosing = false;
                    continue;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            System.out.println("Cheese topping added.");
        }
    }

    private void otherTopping(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nOther Toppings");
            System.out.println("1) Lettuce");
            System.out.println("2) Peppers");
            System.out.println("3) Onions");
            System.out.println("4) Tomatoes");
            System.out.println("5) Jalapeños");
            System.out.println("6) Cucumbers");
            System.out.println("7) Pickles");
            System.out.println("8) Guacamole");
            System.out.println("9) Mushrooms");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.addTopping("Lettuce");
                    break;
                case "2":
                    sandwich.addTopping("Peppers");
                    break;
                case "3":
                    sandwich.addTopping("Onions");
                    break;
                case "4":
                    sandwich.addTopping("Tomatoes");
                    break;
                case "5":
                    sandwich.addTopping("Jalapeños");
                    break;
                case "6":
                    sandwich.addTopping("Cucumbers");
                    break;
                case "7":
                    sandwich.addTopping("Pickles");
                    break;
                case "8":
                    sandwich.addTopping("Guacamole");
                    break;
                case "9":
                    sandwich.addTopping("Mushrooms");
                    break;
                case "0":
                    choosing = false;
                    continue;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            System.out.println("Topping added.");
        }
    }

    private void sauces(Sandwich sandwich) {
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nSauces");
            System.out.println("1) Mayo");
            System.out.println("2) Mustard");
            System.out.println("3) Ketchup");
            System.out.println("4) Ranch");
            System.out.println("5) Thousand Islands");
            System.out.println("6) Vinaigrette");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    sandwich.addTopping("Sauce: Mayo");
                    break;
                case "2":
                    sandwich.addTopping("Sauce: Mustard");
                    break;
                case "3":
                    sandwich.addTopping("Sauce: Ketchup");
                    break;
                case "4":
                    sandwich.addTopping("Sauce: Ranch");
                    break;
                case "5":
                    sandwich.addTopping("Sauce: Thousand Islands");
                    break;
                case "6":
                    sandwich.addTopping("Sauce: Vinaigrette");
                    break;
                case "0":
                    choosing = false; continue;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            System.out.println("Sauce added.");
        }
    }

    private void toasted(Sandwich sandwich) {
        System.out.println("\nToasted?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.print("Choose: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                sandwich.setToasted(true);
                System.out.println("Sandwich set to toasted.");
                break;
            case "2":
                sandwich.setToasted(false);
                System.out.println("Sandwich set to not toasted.");
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }


    private void checkout(Order order) {
        PriceService priceService = new PriceService();

        double sandwichesTotal = 0.0;
        double drinksTotal = 0.0;
        double chipsTotal = 0.0;

        System.out.println();
        System.out.println("CHECKOUT");
        System.out.println();

        // Sandwiches
        System.out.println("Sandwiches:");
        if (order.getSandwiches().isEmpty()) {
            System.out.println("  None");
        } else {
            int index = 1;
            for (Sandwich s : order.getSandwiches()) {

                double sandwichPrice = s.calculatePrice(priceService);
                sandwichesTotal += sandwichPrice;

                // group toppings same style as builder
                List<String> meats = new ArrayList<>();
                List<String> cheeses = new ArrayList<>();
                List<String> others = new ArrayList<>();
                List<String> sauces = new ArrayList<>();

                for (String t : s.getToppings()) {
                    if (t.startsWith("Meat: ")) {
                        meats.add(t.substring("Meat: ".length()));
                    } else if (t.startsWith("Cheese: ")) {
                        cheeses.add(t.substring("Cheese: ".length()));
                    } else if (t.startsWith("Sauce: ")) {
                        sauces.add(t.substring("Sauce: ".length()));
                    } else {
                        others.add(t);
                    }
                }

                System.out.printf("Sandwich #%d - $%.2f%n", index, sandwichPrice);
                System.out.println("  Size: " + s.getSize() + " inch");
                System.out.println("  Bread: " + s.getBread());
                System.out.println("  Toasted: " + (s.isToasted() ? "yes" : "no"));
                System.out.println("  Meats: " + (meats.isEmpty() ? "none" : String.join(", ", meats)));
                System.out.println("  Cheeses: " + (cheeses.isEmpty() ? "none" : String.join(", ", cheeses)));
                System.out.println("  Other toppings: " + (others.isEmpty() ? "none" : String.join(", ", others)));
                System.out.println("  Sauces: " + (sauces.isEmpty() ? "none" : String.join(", ", sauces)));
                System.out.println();

                index++;
            }
            System.out.printf("Sandwich subtotal: $%.2f%n", sandwichesTotal);
        }

        System.out.println();

        // Drinks
        System.out.println("Drinks:");
        if (order.getDrinks().isEmpty()) {
            System.out.println("  None");
        } else {
            int index = 1;
            for (Drink d : order.getDrinks()) {
                double price = priceService.drinkPrice(d.getSize());
                drinksTotal += price;

                System.out.printf("  Drink #%d - size: %s, flavor: %s - $%.2f%n",
                        index, d.getSize(), d.getFlavor(), price);
                index++;
            }
            System.out.printf("Drinks subtotal: $%.2f%n", drinksTotal);
        }

        System.out.println();

        // Chips
        System.out.println("Chips:");
        if (order.getChips().isEmpty()) {
            System.out.println("  None");
        } else {
            int index = 1;
            for (Chips c : order.getChips()) {
                double price = priceService.chipsPrice();
                chipsTotal += price;

                System.out.printf("  Chips #%d - type: %s - $%.2f%n",
                        index, c.getType(), price);
                index++;
            }
            System.out.printf("Chips subtotal: $%.2f%n", chipsTotal);
        }

        System.out.println();

        double total = sandwichesTotal + drinksTotal + chipsTotal;
        System.out.printf("Order total: $%.2f%n", total);
        System.out.println();
        System.out.println("1) Confirm Order");
        System.out.println("0) Cancel");

        boolean choosing = true;
        while (choosing) {
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    confirmOrder(order, total);
                    choosing = false;
                    break;
                case "0":
                    System.out.println("Order cancelled.");
                    choosing = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }



    // Writes a detailed receipt with per item prices and subtotals.
    private void confirmOrder(Order order, double total) {
        PriceService priceService = new PriceService();

        double sandwichesTotal = 0.0;
        double drinksTotal = 0.0;
        double chipsTotal = 0.0;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = "receipt-" + now.format(formatter) + ".txt";

        // Make sure receipts folder exists
        java.io.File receiptsDir = new java.io.File("receipts");
        receiptsDir.mkdirs();

        try (FileWriter writer = new FileWriter(new java.io.File(receiptsDir, fileName))) {

            writer.write("VERY DELI SANDWICHES RECEIPT\n");
            writer.write("Time: " + now + "\n\n");

            // --- Sandwiches section ---
            writer.write("Sandwiches\n");
            if (order.getSandwiches().isEmpty()) {
                writer.write("  None ($0.00)\n\n");
            } else {
                int index = 1;
                for (Sandwich s : order.getSandwiches()) {

                    double sandwichPrice = s.calculatePrice(priceService);
                    sandwichesTotal += sandwichPrice;

                    // Group toppings
                    List<String> meats = new ArrayList<>();
                    List<String> cheeses = new ArrayList<>();
                    List<String> others = new ArrayList<>();
                    List<String> sauces = new ArrayList<>();

                    for (String t : s.getToppings()) {
                        if (t.startsWith("Meat: ")) {
                            meats.add(t.substring("Meat: ".length()));
                        } else if (t.startsWith("Cheese: ")) {
                            cheeses.add(t.substring("Cheese: ".length()));
                        } else if (t.startsWith("Sauce: ")) {
                            sauces.add(t.substring("Sauce: ".length()));
                        } else {
                            others.add(t);
                        }
                    }

                    writer.write(String.format("Sandwich #%d - $%.2f%n", index, sandwichPrice));
                    writer.write("  Size: " + s.getSize() + " inch\n");
                    writer.write("  Bread: " + s.getBread() + "\n");
                    writer.write("  Toasted: " + (s.isToasted() ? "yes" : "no") + "\n");
                    writer.write("  Meats: " + (meats.isEmpty() ? "none" : String.join(", ", meats)) + "\n");
                    writer.write("  Cheeses: " + (cheeses.isEmpty() ? "none" : String.join(", ", cheeses)) + "\n");
                    writer.write("  Other toppings: " + (others.isEmpty() ? "none" : String.join(", ", others)) + "\n");
                    writer.write("  Sauces: " + (sauces.isEmpty() ? "none" : String.join(", ", sauces)) + "\n\n");

                    index++;
                }
                writer.write(String.format("Sandwich subtotal: $%.2f%n%n", sandwichesTotal));
            }

            // --- Drinks section ---
            writer.write("Drinks\n");
            if (order.getDrinks().isEmpty()) {
                writer.write("  None ($0.00)\n\n");
            } else {
                int index = 1;
                for (Drink d : order.getDrinks()) {
                    double price = priceService.drinkPrice(d.getSize());
                    drinksTotal += price;

                    writer.write(String.format(
                            "  Drink #%d - size: %s, flavor: %s - $%.2f%n",
                            index, d.getSize(), d.getFlavor(), price
                    ));
                    index++;
                }
                writer.write(String.format("Drinks subtotal: $%.2f%n%n", drinksTotal));
            }

            // --- Chips section ---
            writer.write("Chips\n");
            if (order.getChips().isEmpty()) {
                writer.write("  None ($0.00)\n\n");
            } else {
                int index = 1;
                for (Chips c : order.getChips()) {
                    double price = priceService.chipsPrice();
                    chipsTotal += price;

                    writer.write(String.format(
                            "  Chips #%d - type: %s - $%.2f%n",
                            index, c.getType(), price
                    ));
                    index++;
                }
                writer.write(String.format("Chips subtotal: $%.2f%n%n", chipsTotal));
            }

            double grandTotal = sandwichesTotal + drinksTotal + chipsTotal;

            // --- Totals block ---
            writer.write("Totals\n");
            writer.write(String.format("  Sandwiches: $%.2f%n", sandwichesTotal));
            writer.write(String.format("  Drinks:     $%.2f%n", drinksTotal));
            writer.write(String.format("  Chips:      $%.2f%n", chipsTotal));
            writer.write(String.format("  GRAND TOTAL: $%.2f%n", grandTotal));

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
            return;
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Order confirmed. Receipt saved as: receipts/" + fileName);
    }
    private void addSpacing() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
}
