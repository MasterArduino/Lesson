package guru;

class DeadlockExample {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();
        Object resource3 = new Object();

        // Поток 1
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Holding resource 1 and 2");
                }
            }
        });

        // Поток 2
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding resource 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for resource 3");
                synchronized (resource3) {
                    System.out.println("Thread 2: Holding resource 2 and 3");
                }
            }
        });

        // Поток 3
        Thread thread3 = new Thread(() -> {
            synchronized (resource3) {
                System.out.println("Thread 3: Holding resource 3");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 3: Waiting for resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 3: Holding resource 1 and 3");
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
