package org.example;

import java.util.List;

public class RemoveProductCommand implements Command {
    private final List<String> order;
    private final String product;

    public RemoveProductCommand(List<String> order, String product) {
        this.order = order;
        this.product = product;
    }

    @Override
    public void execute() {
        order.remove(product);
        System.out.println("Removed product: " + product);
    }

    @Override
    public void undo() {
        order.add(product);
        System.out.println("Restored product: " + product);
    }
}
