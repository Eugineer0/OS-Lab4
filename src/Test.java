import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {

    private static final int capacity = 16;

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        MyQueue myQueue= new MyQueue(queue, capacity);

        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();

        for(int i = 0; i < Integer.parseInt(args[0]); i++) {
            producers.add(new Producer(myQueue, Double.parseDouble(args[1]), i + 1));
            consumers.add(new Consumer(myQueue, i + 1));

            producers.get(i).start();
            consumers.get(i).start();
        }

        while(Producer.totalNumberOfValues < Integer.parseInt(args[2])) {

        }

        for(int i = 0; i < Integer.parseInt(args[0]); i++) {
            producers.get(i).stop();
            consumers.get(i).stop();
        }


        System.out.printf("\n\n\n\t\tThe end\n\n\n");
    }
}
