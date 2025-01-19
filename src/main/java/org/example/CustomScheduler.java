package org.example;

import java.util.concurrent.*;
import java.util.List;

public class CustomScheduler {
    private final ScheduledExecutorService scheduler;
    private final ExecutorService taskExecutor;

    // Konstruktor przyjmujący liczbę wątków
    public CustomScheduler(int threadPoolSize) {
        this.scheduler = Executors.newScheduledThreadPool(1); // Scheduler do planowania
        this.taskExecutor = Executors.newFixedThreadPool(threadPoolSize); // Pool do uruchamiania zadań
    }

    // Metoda do planowania jednorazowego zadania
    public void scheduleTask(Runnable task, long delay, TimeUnit unit) {
        scheduler.schedule(() -> taskExecutor.submit(task), delay, unit);
    }

    // Metoda do uruchamiania listy zadań równolegle
    public <T> List<Future<T>> executeTasks(List<Callable<T>> tasks) throws InterruptedException {
        return taskExecutor.invokeAll(tasks); // Równoległe uruchomienie zadań
    }

    // Metoda do planowania cyklicznego zadania
    public void scheduleRepeatingTask(Runnable task, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(() -> taskExecutor.submit(task), initialDelay, period, unit);
    }

    // Zamykanie schedulera i executorów
    public void shutdown() {
        scheduler.shutdown();
        taskExecutor.shutdown();
    }
}