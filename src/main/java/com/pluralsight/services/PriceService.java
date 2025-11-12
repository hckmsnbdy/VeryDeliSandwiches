package com.pluralsight.services;

import static com.pluralsight.models.SandwichSize.*;

public class PriceService {

    public double priceOfBread(String size) {
        if (FOUR_INCH.equals(size))   return 5.50;
        if (EIGHT_INCH.equals(size))  return 7.00;
        if (TWELVE_INCH.equals(size)) return 8.50;
        return 0.0; // unknown size
    }

    public double priceOfMeat(String size) {
        if (FOUR_INCH.equals(size))   return 1.00;
        if (EIGHT_INCH.equals(size))  return 2.00;
        if (TWELVE_INCH.equals(size)) return 3.00;
        return 0.0;
    }

    public double priceOfCheese(String size) {
        if (FOUR_INCH.equals(size))   return 0.75;
        if (EIGHT_INCH.equals(size))  return 1.50;
        if (TWELVE_INCH.equals(size)) return 2.25;
        return 0.0;
    }

    public double extraMeat(String size) {
        if (FOUR_INCH.equals(size))   return 0.50;
        if (EIGHT_INCH.equals(size))  return 1.00;
        if (TWELVE_INCH.equals(size)) return 1.50;
        return 0.0;
    }

    public double extraCheese(String size) {
        if (FOUR_INCH.equals(size))   return 0.30;
        if (EIGHT_INCH.equals(size))  return 0.60;
        if (TWELVE_INCH.equals(size)) return 0.90;
        return 0.0;
    }

    public double drinkPrice(String size) {
        if (size == null) return 0.0;
        String s = size.trim().toLowerCase();
        switch (s) {
            case "small":  return 2.00;
            case "medium": return 2.50;
            case "large":  return 3.00;
            default:       return 0.0; // unknown size
        }
    }

    public double chipsPrice() {
        return 1.50;
    }
}
