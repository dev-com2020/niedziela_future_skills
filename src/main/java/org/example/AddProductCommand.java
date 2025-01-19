package org.example;

import java.util.List;

public class AddProductCommand implements Command {
    private final List<String> order;
    private final String product;

    public AddProductCommand(List<String> order, String product) {
        this.order = order;
        this.product = product;
    }

    @Override
    public void execute() {
        order.add(product);
        System.out.println("Added product: " + product);
    }

    @Override
    public void undo() {
        order.remove(product);
        System.out.println("Removed product: " + product);
    }
}
