package extras.queue;


import epi.hackathon.MinHeap;

public class MinPriorityQueue {

    private final MinHeap heap;

    public MinPriorityQueue(int capacity) {
        heap = new MinHeap(capacity);
    }

    public void put(int item) {
        heap.insert(item);
    }

    public int take() {
        return heap.extract_min();
    }

}
