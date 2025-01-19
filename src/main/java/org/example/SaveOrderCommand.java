package org.example;

import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveOrderCommand implements Command {
    private final List<String> order;
    private final String filePath;

    public SaveOrderCommand(List<String> order, String filePath) {
        this.order = order;
        this.filePath = filePath;

    }

    @Override
    public void execute() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String item : order) {
                writer.write(item + "\n");
            }
            System.out.println("Order saved " + filePath);
        } catch (IOException e) {
            System.out.println("Błąd zapisywania: " + e.getMessage());
        }
    }
        @Override
        public void undo() {
            java.io.File file = new java.io.File(filePath);
            if (file.exists() && file.delete()) {
                System.out.println("Plik usunięty " + filePath);
            } else {
                System.out.println("No file to delete " + filePath);
            }
        }
    }

