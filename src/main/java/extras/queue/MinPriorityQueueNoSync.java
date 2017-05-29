package extras.queue;


import epi.hackathon.MinHeap;

public class MinPriorityQueueNoSync implements MinPriorityQueue {

    private final MinHeap heap;

    public MinPriorityQueueNoSync(int capacity) {
        heap = new MinHeap(capacity);
    }

    @Override
    public void put(int item) {
        heap.insert(item);
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
