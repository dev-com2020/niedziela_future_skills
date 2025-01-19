package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Lista reprezentująca zamówienie
        List<String> order = new ArrayList<>();

        // Tworzenie Invokera
        CommandInvoker invoker = new CommandInvoker();

        // Dodanie produktów do zamówienia
        Command addProduct1 = new AddProductCommand(order, "Laptop");
        Command addProduct2 = new AddProductCommand(order, "Smartphone");

        invoker.executeCommand(addProduct1);
        invoker.executeCommand(addProduct2);

        // Usunięcie produktu z zamówienia
//        Command removeProduct = new RemoveProductCommand(order, "Laptop");
//        invoker.executeCommand(removeProduct);

        String filePath = "zamowienie.txt";
        Command saveOrder = new SaveOrderCommand(order, filePath);
        invoker.executeCommand(saveOrder);

        // Cofnięcie ostatniej operacji
        invoker.undoLastCommand();

        // Wyświetlenie bieżącego stanu zamówienia
        System.out.println("Current order: " + order);

        // Cofnięcie kolejnej operacji
        invoker.undoLastCommand();
        System.out.println("After another undo: " + order);
    }
}
