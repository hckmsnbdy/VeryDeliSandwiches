package com.pluralsight.models;

import com.pluralsight.services.PriceService;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich s) {
        if (s != null) sandwiches.add(s);
    }

    public void addDrink(Drink d) {
        if (d != null) drinks.add(d);
    }

    public void addChips(Chips c) {
        if (c != null) chips.add(c);
    }

    public List<Sandwich> getSandwiches() { return sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Chips> getChips() { return chips; }

    public double calculateTotal(PriceService priceService) {
        double total = 0.0;

        for (Sandwich s : sandwiches) {
            total += s.calculatePrice(priceService);
        }

        for (Drink d : drinks) {
            total += priceService.drinkPrice(d.getSize());
        }

        for (Chips c : chips) {
            total += priceService.chipsPrice();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sandwiches=" + sandwiches +
                ", drinks=" + drinks +
                ", chips=" + chips +
                '}';
    }
}
