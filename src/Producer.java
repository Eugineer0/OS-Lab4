import java.util.Random;

public class Producer extends Thread {
    public static int totalNumberOfValues = 0;

    private int id;
    private final int delayInMillis;
    private MyQueue queue;

    Producer(MyQueue queue, double delayInSeconds, int id) {
        this.id = id;
        this.queue = queue;
        this.delayInMillis = (int)delayInSeconds * 1000;
    }

    @Override
    public void run() {
        while (true) {
            totalNumberOfValues++;

            queue.put(new Random().nextInt(10), id);
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
