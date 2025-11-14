package com.pluralsight.services;

public class ConsolePrinter implements InterfacePrint {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
