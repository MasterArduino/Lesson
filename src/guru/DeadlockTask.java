package guru;

public class DeadlockTask {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();
        Object resource3 = new Object();

        // Поток 1
        Thread thread1 = new Thread(() -> {

            synchronized (resource1) {
                System.out.println("Поток 1: Удерживается ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 1: Ожидание ресурса 2");
                synchronized (resource2) {
                    System.out.println("Поток 1: Удерживается ресурс 1 и 2");
                }
            }
        });

        // Поток 2
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Поток 2: Удерживается ресурс 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 2: Ожидание ресурса 3");
                synchronized (resource3) {
                    System.out.println("Поток 2: Удерживается ресурс 2 и 3");
                }
            }
        });

        // Поток 3
        Thread thread3 = new Thread(() -> {
            synchronized (resource3) {
                System.out.println("Поток 3: Удерживается ресурс 3");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 3: Ожидание ресурса 1");
                synchronized (resource1) {
                    System.out.println("Поток 3: Удерживается ресурс 1 и 3");
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
