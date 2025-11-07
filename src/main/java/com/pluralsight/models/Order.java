package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.sandwiches = new ArrayList<Sandwich>();
        this.drinks = new ArrayList<Drink>();
        this.chips = new ArrayList<Chips>();
    }

    public void addSandwich(Sandwich s) {
        if (s != null) {
            sandwiches.add(s);
        }
    }

    public void addDrink(Drink d) {
        if (d != null) {
            drinks.add(d);
        }
    }

    public void addChips(Chips c) {
        if (c != null) {
            chips.add(c);
        }
    }

    public List<Sandwich> getSandwiches() { return sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Chips> getChips() { return chips; }

    // public double calculateTotal(PriceService priceService) { return 0.0; }

    @Override
    public String toString() {
        return "Order{sandwiches=" + sandwiches + ", drinks=" + drinks + ", chips=" + chips + "}";
    }
}

