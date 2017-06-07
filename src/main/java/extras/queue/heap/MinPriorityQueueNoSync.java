package extras.queue.heap;


import epi.hackathon.MinHeap;
import extras.queue.Queues;

public class MinPriorityQueueNoSync implements Queues.MinPriorityQueue {

    private final MinHeap heap;

    public MinPriorityQueueNoSync(int capacity) {
        heap = new MinHeap(capacity);
    }

    @Override
    public boolean put(int item) {
        heap.insert(item);
        return true;
    }

    @Override
    public int take() {
        return heap.extract_min();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
