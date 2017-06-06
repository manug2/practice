package epi.hackathon;


import java.util.Arrays;

import static epi.hackathon.MinHeap.from;
import static java.lang.String.format;

public class MinHeap {
    final int[] data;
    int size;

    public String toString() {
        return Arrays.toString(getData());
    }

    public MinHeap(int length) {
        this.data = new int[length];
        this.size=0;
    }

    private MinHeap(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    void heapify(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);

        if (left <= size - 1
                && data[left] < data[smallest])
            smallest = left;

        if (right <= size - 1
                && data[right] < data[smallest])
            smallest = right;

        if (smallest != i) {
            swap(smallest, i);
            heapify(smallest);
        }
    }

    private int right(int i) {
        return 2*i+2;
    }

    private int left(int i) {
        return 2*i+1;
    }

    private void swap(int largest, int i) {
        int tmp = data[i];
        data[i] = data[largest];
        data[largest] = tmp;
    }

    static MinHeap from(int[] arr) {
        MinHeap heap = new MinHeap(arr);
        for (int i = arr.length / 2; i >= 0; i--)
            heap.heapify(i);
        return heap;
    }

    int min() {
        return data[0];
    }

    public int extract_min() {
        if (size==0)
            throw new IndexOutOfBoundsException(
                    format("heap is empty"));

        int h_max = data[0];
        data[0] = data[size-1];
        data[size-1] = 0;
        size--;
        heapify(0);
        return h_max;
    }

    public void insert(int item) {
        if (size==data.length)
            throw new IndexOutOfBoundsException(
                    format("heap has reached max capacity (%s)", size));

        data[size] = Integer.MAX_VALUE;
        size++;
        decrease_key(size-1, item);
    }

    private void decrease_key(int loc, int item) {
        if (item > data[loc])
            throw new RuntimeException();
        data[loc] = item;
        int i = loc;
        int p = parent(i);
        while (i > 0 && data[p] > data[i]) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }

    private int parent(int i) {
        return i/2;
    }

    public int[] getData() {
        return Arrays.copyOf(data, size);
    }

    public void clear() {
        size=0;
    }

    public boolean isFull() {
        return size==data.length;
    }

    public boolean isEmpty() {
        return size==0;
    }
}


class HeapSort {

    int[] ascending(int[] data) {
        int[] output = new int[data.length];
        MinHeap h = from (data);
        for (int i=0; i< data.length; i++) {
            output[i] = h.extract_min();
        }
        return output;
    }
}