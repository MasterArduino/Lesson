package guru;

import java.util.LinkedList;

class Queue {
    private final LinkedList<Integer> queue = new LinkedList<>();

    public synchronized void produce(int item) {
        while (queue.size() >= 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(item);
        System.out.println("Produced: " + item + " Queue size: " + queue.size());
        notifyAll();
    }

    public synchronized int consume() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int item = queue.remove();
        System.out.println("Consumed: " + item + " Queue size: " + queue.size());
        notifyAll();
        return item;
    }

    public synchronized int getSize() {
        return queue.size();
    }
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

class Producer implements Runnable {
    private final Queue queue;

    Producer(Queue q) {
        queue = q;
    }

    public void run() {
        while (Main.count < 10000) {
            int number = (int) (Math.random() * 100) + 1;
            queue.produce(number);
            Main.count++;
            if (queue.getSize() <= 80) {
                synchronized (queue) {
                    queue.notifyAll();
                }
            }
            if (queue.getSize() >= 100) {
                try {
                    synchronized (queue) {
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

class Consumer implements Runnable {
    private final Queue queue;

    Consumer(Queue q) {
        queue = q;
    }

    public void run() {
        while (Main.count < 10000) {
            int number = queue.consume();
            Main.count++;
            if (queue.getSize() >= 100) {
                synchronized (queue) {
                    queue.notifyAll();
                }
            }
            if (queue.isEmpty()) {
                try {
                    synchronized (queue) {
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

public class Main {
    public static int count = 0;

    public static void main(String[] args) {
        Queue q = new Queue();
        Producer producer1 = new Producer(q);
        Producer producer2 = new Producer(q);
        Producer producer3 = new Producer(q);
        Consumer consumer1 = new Consumer(q);
        Consumer consumer2 = new Consumer(q);

        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer2);
        Thread t3 = new Thread(producer3);
        Thread t4 = new Thread(consumer1);
        Thread t5 = new Thread(consumer2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
