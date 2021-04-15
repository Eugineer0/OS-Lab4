import java.util.Random;

public class Producer extends Thread {
    public static int totalNumberOfValues = 0;

    private int id;
    private final int delayInMilliseconds;
    private MyQueue queue;

    Producer(MyQueue queue, double delayInSeconds, int id) {
        this.id = id;
        this.queue = queue;
        this.delayInMilliseconds = (int)delayInSeconds * 1000;
    }

    @Override
    public void run() {
        while (true) {
            totalNumberOfValues++;

            queue.put(new Random().nextInt(10), id);
            try {
                Thread.sleep(delayInMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
