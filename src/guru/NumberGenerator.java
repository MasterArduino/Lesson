package guru;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class NumberGenerator implements Callable<String> {

    private final String threadName;

    public NumberGenerator(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String call() {
        List<Integer> numbers = generateNumbers();
        int sum = calculateSum(numbers);
        try {
            Thread.sleep(new Random().nextInt(10000) + 1000); // Засыпаем на 1-10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Thread " + threadName + ": Sum of numbers = " + sum;
    }

    private List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(100));
        }
        return numbers;
    }

    private int calculateSum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Callable<String> task = new NumberGenerator("Thread-" + i);
            Future<String> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
