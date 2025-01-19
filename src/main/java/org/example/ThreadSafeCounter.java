package org.example;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class ThreadSafeCounter {
    private int count = 0;
    private static final VarHandle COUNTER_HANDLE;

    static {
        try {
            COUNTER_HANDLE = MethodHandles
                    .lookup()
                    .findVarHandle(ThreadSafeCounter.class, "count", int.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int incrementAndGet() {
//        Atomowe dodanie do licznika
        return (int) COUNTER_HANDLE.getAndAdd(this, 1) + 1;
    }

    public static void main(String[] args) {
        ThreadSafeCounter counter = new ThreadSafeCounter();

//        uruchomienie wątków
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - Counter " + counter.incrementAndGet());
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }
}

