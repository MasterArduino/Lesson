package guru;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TimerTask implements Callable<Void> {

    private final String threadName;

    public TimerTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Void call() {
        synchronized (this) {
            writeToFile("Hello World " + LocalDateTime.now());
            System.out.println("Thread " + threadName + " finished writing to file.");
        }
        return null;
    }

    private void writeToFile(String message) {
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Callable<Void> task = new TimerTask("Thread-" + i);
            Future<Void> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
