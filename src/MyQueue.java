import java.util.concurrent.ConcurrentLinkedQueue;

public class MyQueue {

    private int capacity;
    public ConcurrentLinkedQueue<Integer> queue;

    public MyQueue(int capacity) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.capacity = capacity;
    }

    public synchronized int get(int id) {
        while (queue.isEmpty()) {
            try {
                System.out.printf("\t\tConsumer {%d} will wait: Queue is empty!\n", id);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int current = queue.poll();
        notify();

        System.out.printf("\t\u001b[32mConsumer %d gets: %d\u001b[0m\n", id, current);
        return current;
    }

    public synchronized void put(int value, int id)  {
        while(queue.size() >= capacity){
            try {
                System.out.printf("\t\tProducer {%d} will wait: Queue is full!\n", id);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\t\u001b[34mProducer %d adds: %d\u001b[0m\n", id, value);
        queue.add(value);
        notify();
    }
}
