package com.pluralsight.models;

public class Chips {
    private String type;

    public Chips(String type) {
        if (type == null || type.isEmpty()) {
            this.type = "classic";
        } else {
            this.type = type.trim();
        }
    }

    public String getType() { return type; }
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            type = "classic";
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "Chips{type='" + type + "'}";
    }
}

