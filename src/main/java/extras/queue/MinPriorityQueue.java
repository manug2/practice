package extras.queue;


public interface MinPriorityQueue {
    void put(int item);

    int take();

    void clear();
}
