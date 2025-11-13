package com.pluralsight.ui;

import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
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
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final PriceService priceService = new PriceService();
    private String pendingDrinkSize;
    private String pendingDrinkFlavor;


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
        // Start with a default sandwich and let the user customize it
        Sandwich sandwich = new Sandwich();

        boolean editing = true;

        while (editing) {
            System.out.println("\nSandwich Builder");
            System.out.println("Current: " + sandwich.toString());
            System.out.println("1) Select bread");
            System.out.println("2) Select size");
            System.out.println("3) Toppings");
            System.out.println("4) Sauces");
            System.out.println("5) Toasted?");
            System.out.println("9) Add sandwich to order");
            System.out.println("0) Cancel");
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
                case "9":
                    order.addSandwich(sandwich);
                    System.out.println("Sandwich added to order.");
                    editing = false;
                    break;
                case "0":
                    System.out.println("Sandwich cancelled.");
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
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

                System.out.println("Added drink: " + d.toString());

                pendingDrinkSize = null;
                pendingDrinkFlavor = null;

                ordering = false;
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
        System.out.println("\nSelect size: 1) Small  2) Medium  3) Large");
        System.out.print("Choose: ");
        String input = scanner.nextLine();

        if ("1".equals(input)) {
            pendingDrinkSize = "small";
        } else if ("2".equals(input)) {
            pendingDrinkSize = "medium";
        } else if ("3".equals(input)) {
            pendingDrinkSize = "large";
        } else {
            System.out.println("Invalid size.");
            return;
        }

        System.out.println("Size selected: " + pendingDrinkSize);
    }
    private void chooseDrinkFlavor(Order order) {
        System.out.println("\nSelect flavor: 1) Cola  2) Orange  3) Lemonade");
        System.out.print("Choose: ");
        String input = scanner.nextLine();

        if ("1".equals(input)) {
            pendingDrinkFlavor = "cola";
        } else if ("2".equals(input)) {
            pendingDrinkFlavor = "orange";
        } else if ("3".equals(input)) {
            pendingDrinkFlavor = "lemonade";
        } else {
            System.out.println("Invalid flavor.");
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
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nSize");
            System.out.println("1) 4 inch");
            System.out.println("2) 8 inch");
            System.out.println("3) 12 inch");
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
        if (order.getSandwiches().isEmpty()
                && order.getDrinks().isEmpty()
                && order.getChips().isEmpty()) {
            System.out.println("\nCHECKOUT");
            System.out.println("Order is empty. Please add at least one item before checkout.");
            return;
        }

        PriceService priceService = new PriceService();
        boolean checkingOut = true;

        while (checkingOut) {
            System.out.println("\nCHECKOUT");

            // Show Sandwiches
            System.out.println("\nSandwiches:");
            if (order.getSandwiches().isEmpty()) {
                System.out.println("- None");
            } else {
                for (int i = 0; i < order.getSandwiches().size(); i++) {
                    System.out.println("- " + order.getSandwiches().get(i));
                }
            }

            // Show Drinks
            System.out.println("\nDrinks:");
            if (order.getDrinks().isEmpty()) {
                System.out.println("- None");
            } else {
                for (int i = 0; i < order.getDrinks().size(); i++) {
                    System.out.println("- " + order.getDrinks().get(i));
                }
            }

            // Show Chips
            System.out.println("\nChips:");
            if (order.getChips().isEmpty()) {
                System.out.println("- None");
            } else {
                for (int i = 0; i < order.getChips().size(); i++) {
                    System.out.println("- " + order.getChips().get(i));
                }
            }

            // Calculate and show total
            double total = order.calculateTotal(priceService);
            System.out.printf("\nTotal Price: $%.2f\n", total);

            System.out.println("\n1) Confirm Order");
            System.out.println("0) Cancel");
            System.out.print("Choose: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // confirm and write receipt
                    confirmOrder(order, total);
                    checkingOut = false;
                    break;

                case "0":
                    System.out.println("Checkout cancelled.");
                    checkingOut = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }
    }


    private void confirmOrder(Order order, double total) {
        try {

            DateTimeFormatter frmt = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
            String fileName = "receipt-" + LocalDateTime.now().format(frmt) + ".txt";

            FileWriter writer = new FileWriter(fileName);

            writer.write("VERY DELI SANDWICHES\n");
            writer.write("Time: " + LocalDateTime.now() + "\n\n");

            writer.write("Sandwiches\n");
            if (order.getSandwiches().isEmpty()) {
                writer.write("None\n");
            } else {
                for (int i = 0; i < order.getSandwiches().size(); i++) {
                    writer.write((i + 1) + ") " + order.getSandwiches().get(i).toString() + "\n");
                }
            }

            writer.write("\nDrinks\n");
            if (order.getDrinks().isEmpty()) {
                writer.write("None\n");
            } else {
                for (int i = 0; i < order.getDrinks().size(); i++) {
                    writer.write((i + 1) + ") " + order.getDrinks().get(i).toString() + "\n");
                }
            }

            writer.write("\nChips\n");
            if (order.getChips().isEmpty()) {
                writer.write("None\n");
            } else {
                for (int i = 0; i < order.getChips().size(); i++) {
                    writer.write((i + 1) + ") " + order.getChips().get(i).toString() + "\n");
                }
            }

            writer.write(String.format("\nTOTAL: $%.2f\n", total));

            writer.close();
            System.out.println("Order confirmed. Receipt saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to write receipt: " + e.getMessage());
        }
    }


}
