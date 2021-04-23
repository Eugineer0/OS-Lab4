public class Consumer extends Thread {
    private int id;
    private MyQueue queue;

    public Consumer(MyQueue queue, int id)
    {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        while(true) {
            int value = queue.get(id);

            sleep(3000); // only for demonstration

            System.out.printf("Calculated by Thread {%d}: %d\n", id, fact(value));
        }
    }

    private static void sleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int fact(int value) {
        int result = 1;

        for(int i = 2; i <= value; i++) {
            result *= i;
        }

        return result;
    }
}
