package extras.queue;


import java.util.concurrent.locks.ReentrantLock;


public class MinPriorityQueueSkipList implements Queues.MinPriorityQueue, Queues.BlockingQueue {

    private final SkipList heap;
    private final ReentrantLock lock;

    public MinPriorityQueueSkipList(int capacity) {
        heap = new SkipList(capacity);
        lock = new ReentrantLock();
    }

    @Override
    public boolean put(int item) {
        try {
            if (! heap.isFull()) {
                heap.add(item);
                return true;
            }
        } finally {
        }
        return false;
    }

    @Override
    public int take() {
        try {
            if (heap.isEmpty())
                return Integer.MIN_VALUE;
            else
                return heap.extract_min();
        } finally {
        }
    }

    @Override
    public void clear() {
        try {
            if(! heap.isEmpty())
                heap.clear();
        } finally {
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void offer(int item) throws InterruptedException {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                 lock.lockInterruptibly();
                if (!heap.isFull()) {
                    heap.add(item);
                    return;
                } else
                    lock.unlock();
            }
            throw new InterruptedException("put was interrupted");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int poll() throws InterruptedException {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                lock.lockInterruptibly();
                if (! heap.isEmpty())
                    return heap.extract_min();
                else
                    lock.unlock();
            }
            throw new InterruptedException("take was interrupted");
        } finally {
            lock.unlock();
        }
    }

}
