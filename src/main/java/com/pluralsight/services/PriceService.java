package com.pluralsight.services;

import static com.pluralsight.models.SandwichSize.*;

public class PriceService {

    public double priceOfBread(String size) {
        switch (size) {
            case "4":  return 4.50;
            case "8":  return 6.50;
            case "12": return 8.50;
            default:   return 0.0;
        }
    }

    public double priceOfMeat(String size) {
        switch (size) {
            case "4":  return 1.00;
            case "8":  return 1.75;
            case "12": return 2.50;
            default:   return 0.0;
        }
    }

    public double priceOfCheese(String size) {
        switch (size) {
            case "4":  return 0.75;
            case "8":  return 1.25;
            case "12": return 1.75;
            default:   return 0.0;
        }
    }

    public double extraMeat(String size) {
        return priceOfMeat(size) * 0.75;
    }

    public double extraCheese(String size) {
        return priceOfCheese(size) * 0.50; // +50% of base cheese price

    }

    public double drinkPrice(String size) {
        switch (size.toLowerCase()) {
            case "small":  return 1.50;
            case "medium": return 2.00;
            case "large":  return 2.50;
            default:       return 0.0;
        }
    }

    public double chipsPrice() {
        return 1.20;
    }
}
