//ExecutorService was introduced with Java SE-5 in September 2004

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceExample {
    // Shared counter variable
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // Create a fixed thread pool with 10 threads
        // try-with-resources — introduced in Java 7
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            // Submit 10 tasks to the executor
            for (int i = 0; i < 10; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < 1000; j++) {
                        count.incrementAndGet(); // Atomic increment
                    }
                });
            }

            // Shutdown the executor and wait for all tasks to complete
            executor.shutdown();

            // Wait for all threads to finish OPTION 1
            //while (!executor.isTerminated()) {
            //
            //}

            // Wait for all threads to finish Option 2
            boolean terminationResult = executor.awaitTermination(60, TimeUnit.SECONDS);

            System.out.println("Final value: " + count.get());
        } finally {
            System.out.println("finally block");
        }
    }
}