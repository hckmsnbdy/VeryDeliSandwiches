package com.pluralsight.services;

import static com.pluralsight.models.SandwichSize.*;

public class PriceService {

    public double priceOfBread(String size) {
        switch (size) {
            case "4":  return 5.50;
            case "8":  return 7.00;
            case "12": return 8.50;
            default:   return 0.0;
        }
    }

    public double priceOfMeat(String size) {
        switch (size) {
            case "4":  return 1.00;
            case "8":  return 2.00;
            case "12": return 3.00;
            default:   return 0.0;
        }
    }

    public double priceOfCheese(String size) {
        switch (size) {
            case "4":  return 0.75;
            case "8":  return 1.50;
            case "12": return 2.25;
            default:   return 0.0;
        }
    }

    public double extraMeat(String size) {
        switch (size) {
            case "4":
                return 0.50;
            case "8":
                return 1.00;
            case "12":
                return 1.50;
            default:
                return 0.0;
        }
    }


    public double extraCheese(String size) {
        switch (size) {
            case "4":
                return 0.30;
            case "8":
                return 0.60;
            case "12":
                return 0.90;
            default:
                return 0.0;
        }
    }


    public double drinkPrice(String size) {
        switch (size.toLowerCase()) {
            case "small":  return 2.00;
            case "medium": return 2.50;
            case "large":  return 3.00;
            default:       return 0.0;
        }
    }

    public double chipsPrice() {
        return 1.50;
    }
}
