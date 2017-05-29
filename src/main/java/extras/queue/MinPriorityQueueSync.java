package extras.queue;


import epi.hackathon.MinHeap;

public class MinPriorityQueueSync implements MinPriorityQueue {

    private final MinHeap heap;

    public MinPriorityQueueSync(int capacity) {
        heap = new MinHeap(capacity);
    }

    @Override
    public synchronized void put(int item) {
        heap.insert(item);
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
