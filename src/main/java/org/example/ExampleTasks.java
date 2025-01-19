package org.example;

import java.util.concurrent.Callable;

public class ExampleTasks {
    public static Runnable simpleTask(String name) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " - Task: " + name + " started.");
            try {
                Thread.sleep(2000); // Symulacja długiego zadania
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " - Task: " + name + " completed.");
        };
    }

    public static Callable<String> callableTask(String name) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " - Callable Task: " + name + " started.");
            Thread.sleep(2000); // Symulacja długiego zadania
            System.out.println(Thread.currentThread().getName() + " - Callable Task: " + name + " completed.");
            return "Result of " + name;
        };
    }
}
