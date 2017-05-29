package extras.queue;


public interface BlockingMinPriorityQueue extends MinPriorityQueue {
    boolean put(int item, int timeoutMS);

    Integer take(int timeoutMS);

}
