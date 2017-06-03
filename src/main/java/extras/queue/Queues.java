package extras.queue;


public class Queues {

    public static interface MinPriorityQueue {
        boolean put(int item);
        int take();
        void clear();
    }

    public static interface BlockingQueue extends MinPriorityQueue {
        void offer(int item) throws InterruptedException;
        int poll() throws InterruptedException;
    }

    public static interface TimedBlockingQueue extends MinPriorityQueue {
        boolean offer(int item, long timeoutMS) throws InterruptedException;
        int poll(long timeoutMS) throws InterruptedException;
    }

}
