package extras.queue.heap;


import epi.hackathon.MinHeap;
import extras.queue.Queues;

public class MinPriorityQueueSync implements Queues.MinPriorityQueue {

    private final MinHeap heap;

    public MinPriorityQueueSync(int capacity) {
        heap = new MinHeap(capacity);
    }

    @Override
    public synchronized boolean put(int item) {
        heap.insert(item);
        return true;
    }

    @Override
    public synchronized int take() {
        return heap.extract_min();
    }

    @Override
    public synchronized void clear() {
        heap.clear();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
