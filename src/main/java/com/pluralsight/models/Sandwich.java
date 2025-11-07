package com.pluralsight.models;

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
            if (size == null) size = SandwichSize.FOUR_INCH;
            this.size = size;
        }

        public String getBread() { return bread; }
        public void setBread(String bread) {
            if (bread == null) bread = BreadType.WHITE;
            this.bread = bread;
        }

        public boolean isToasted() { return toasted; }
        public void setToasted(boolean toasted) { this.toasted = toasted; }

        public List<String> getToppings() { return toppings; }

        public void addTopping(String topping) {
            if (topping != null && !topping.isEmpty()) {
                toppings.add(topping);
            }
        }

        // public double calculatePrice(PriceService priceService) {return 0.0; }

        @Override
        public String toString() {
            return "Sandwich{size='" + size + "', bread='" + bread +
                    "', toasted=" + toasted + ", toppings=" + toppings + "}";
        }

    }
