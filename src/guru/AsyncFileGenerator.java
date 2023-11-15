package guru;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class AsyncFileGenerator implements Callable<List<String>> {

    private final String threadName;

    public AsyncFileGenerator(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public List<String> call() {
        List<String> fileNames = new ArrayList<>();
        try {
            for (int i = 1; i <= 10; i++) {
                String fileName = "file" + i + ".txt";
                generateFile(fileName);
                fileNames.add(fileName);
                Thread.sleep(new Random().nextInt(3000) + 1000); // Засыпаем на 1-3 секунды
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    private void generateFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < 10; i++) {
                writer.write(generateRandomString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomString() {
        int leftLimit = 97; // ASCII код буквы 'a'
        int rightLimit = 122; // ASCII код буквы 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<List<String>>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Callable<List<String>> task = new AsyncFileGenerator("Thread-" + i);
            Future<List<String>> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<List<String>> future : futures) {
            try {
                List<String> fileNames = future.get();
                System.out.println("Generated files by " + Thread.currentThread().getName() + ": " + fileNames);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
