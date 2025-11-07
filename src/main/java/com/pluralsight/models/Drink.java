package com.pluralsight.models;

public class Drink {
    private String size;   // "small", "medium", "large"
    private String flavor; // "cola", "orange", "lemonade"

    public Drink(String size, String flavor) {
        if (size == null || size.isEmpty()) {
            this.size = "small";
        } else {
            this.size = size.trim();
        }

        if (flavor == null || flavor.isEmpty()) {
            this.flavor = "cola";
        } else {
            this.flavor = flavor.trim();
        }
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            size = "small";
        }
        this.size = size;
    }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) {
        if (flavor == null || flavor.isEmpty()) {
            flavor = "cola";
        }
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "Drink{size='" + size + "', flavor='" + flavor + "'}";
    }
}

