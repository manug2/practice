package epi.hackathon;


public class Ch9StackWithMax {
    int[] items = new int[1000];
    int head=0;
    int[] max_items = new int[items.length];

    public void push(int i) {
        items[head++] = i;
        insert(i);
    }

    private void insert(int item) {
        if (head==1) {
            max_items[head - 1] = item;
            return;
        }

        if (max_items[head-2] > item)
            max_items[head-1] = max_items[head-2];
        else
            max_items[head-1] = item;
    }

    public int pop() {
        if (head==0)
            throw new IllegalStateException();

        return items[--head];
    }

    public int max() {
        return max_items[head-1];
    }
}
