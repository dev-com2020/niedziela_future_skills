package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo6 { public static void main(String[] args) {
    // Tworzenie schedulera z 3 wątkami w puli
    CustomScheduler scheduler = new CustomScheduler(3);

    // 1. Jednorazowe zadanie
    scheduler.scheduleTask(ExampleTasks.simpleTask("One-time Task"), 3, TimeUnit.SECONDS);

    // 2. Zadania równoległe
    List<Callable<String>> tasks = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
        tasks.add(ExampleTasks.callableTask("Task-" + i));
    }

    try {
        List<Future<String>> results = scheduler.executeTasks(tasks);
        for (Future<String> result : results) {
            System.out.println("Result: " + result.get()); // Pobieranie wyników z callable
        }
    } catch (Exception e) {
        System.err.println("Error executing tasks: " + e.getMessage());
    }

    // 3. Cycliczne zadanie
    scheduler.scheduleRepeatingTask(ExampleTasks.simpleTask("Repeating Task"), 5, 10, TimeUnit.SECONDS);

    // Zamykanie schedulera po 30 sekundach
    scheduler.scheduleTask(() -> {
        System.out.println("Shutting down scheduler...");
        scheduler.shutdown();
    }, 30, TimeUnit.SECONDS);
}
}
