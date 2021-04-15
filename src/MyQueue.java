import java.util.concurrent.ConcurrentLinkedQueue;

public class MyQueue {

    private int capacity;
    public ConcurrentLinkedQueue<Integer> queue = null;

    public MyQueue(ConcurrentLinkedQueue<Integer> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    public synchronized int get(int id) {
        while (queue.isEmpty()) {
            try {
                wait();
                System.out.printf("\t\tConsumer %d: Queue is empty!\n", id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int current = queue.poll();
        notify();

        System.out.printf("\tConsumer %d gets: %d\n", id, current);
        return current;
    }

    public synchronized void put(int value, int id)  {
        while(queue.size() >= capacity){
            try {
                wait();
                System.out.printf("\t\tProducer %d: Queue is full, PRODUCERS will wait!\n", id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\tProducer %d adds: %d\n", id, value);
        queue.add(value);
        notify();
    }
}
