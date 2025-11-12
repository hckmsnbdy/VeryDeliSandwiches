package com.pluralsight.models;

import com.pluralsight.services.PriceService;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String size;
    private String bread;
    private boolean toasted;
    private final List<String> toppings;

    public Sandwich() {
        this.size = SandwichSize.FOUR_INCH;
        this.bread = BreadType.WHITE;
        this.toasted = false;
        this.toppings = new ArrayList<>();
    }

    public Sandwich(String size, String bread, boolean toasted) {
        if (size == null) {
            this.size = SandwichSize.FOUR_INCH;
        } else {
            this.size = size;
        }

        if (bread == null) {
            this.bread = BreadType.WHITE;
        } else {
            this.bread = bread;
        }

        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null) {
            size = SandwichSize.FOUR_INCH;
        }
        this.size = size;
    }

    public String getBread() { return bread; }
    public void setBread(String bread) {
        if (bread == null) {
            bread = BreadType.WHITE;
        }
        this.bread = bread;
    }

    public boolean isToasted() { return toasted; }
    public void setToasted(boolean toasted) { this.toasted = toasted; }

    public List<String> getToppings() { return toppings; }

    public void addTopping(String topping) {
        if (topping != null && !topping.isBlank()) {
            toppings.add(topping.trim());
        }
    }

    public double calculatePrice(PriceService priceService) {
        double total = 0.0;

        total += priceService.priceOfBread(size);


        for (String t : toppings) {
            String x = t.toLowerCase().trim();

            if (x.contains("extra meat")) {
                total += priceService.extraMeat(size);
                continue;
            }
            if (x.contains("extra cheese")) {
                total += priceService.extraCheese(size);
                continue;
            }

            if (isMeat(x)) {
                total += priceService.priceOfMeat(size);
                continue;
            }
            if (isCheese(x)) {
                total += priceService.priceOfCheese(size);
                continue;
            }

        }

        return total;
    }

    private boolean isMeat(String x) {
        return x.equals("steak") ||
                x.equals("ham") ||
                x.equals("salami") ||
                x.equals("roast beef") ||
                x.equals("chicken") ||
                x.equals("bacon");
    }

    private boolean isCheese(String x) {
        return x.equals("american") ||
                x.equals("provolone") ||
                x.equals("cheddar") ||
                x.equals("swiss");
    }

    @Override
    public String toString() {
        return "Sandwich{size='" + size + "', bread='" + bread +
                "', toasted=" + toasted + ", toppings=" + toppings + "}";
    }
}