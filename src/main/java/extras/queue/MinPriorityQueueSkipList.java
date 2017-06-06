package extras.queue;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class MinPriorityQueueSkipList implements
        Queues.MinPriorityQueue, Queues.BlockingQueue, Queues.TimedBlockingQueue {

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
                heap.insert(item);
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
        while (! Thread.currentThread().isInterrupted()) {
             lock.lockInterruptibly();
            if (!heap.isFull()) {
                heap.insert(item);
                lock.unlock();
                return;
            } else
                lock.unlock();
        }
        throw new InterruptedException("put was interrupted");
    }

    @Override
    public int poll() throws InterruptedException {
        while (! Thread.currentThread().isInterrupted()) {
            lock.lockInterruptibly();
            if (! heap.isEmpty()) {
                int item = heap.extract_min();
                lock.unlock();
                return item;
            }
            else
                lock.unlock();
        }
        throw new InterruptedException("take was interrupted");
    }

    @Override
    public boolean offer(int item, long timeoutMS) throws InterruptedException {
        final long start = System.nanoTime();
        boolean didLock = false;
        try {
            while (! Thread.currentThread().isInterrupted()) {
                final long availableTime = time(start, timeoutMS);
                if (availableTime==0)
                    return false;

                didLock = lock.tryLock(availableTime, TimeUnit.MILLISECONDS);
                if (didLock && !heap.isFull()) {
                    heap.insert(item);
                    return true;
                }
                else {
                    didLock=false;
                    lock.unlock();
                }
            }
            return false;
        } finally {
            if (didLock)
                lock.unlock();
        }
    }

    @Override
    public int poll(long timeoutMS) throws InterruptedException {
        final long start = System.nanoTime();
        boolean didLock = false;
        try {
            while (! Thread.currentThread().isInterrupted()) {
                final long availableTime = time(start, timeoutMS);
                if (availableTime==0)
                    return Integer.MIN_VALUE;

                didLock = lock.tryLock(availableTime, TimeUnit.MILLISECONDS);
                if (didLock && ! heap.isEmpty())
                    return heap.extract_min();
                else {
                    didLock=false;
                    lock.unlock();
                }
            }
            throw new InterruptedException("take was interrupted");
        } finally {
            if (didLock)
                lock.unlock();
        }
    }

    long time (final long start, final long timeoutMS) {
        final long elapsedTime = (System.nanoTime() - start);
        final long availableTime = timeoutMS*1000L - elapsedTime;
        return availableTime>5L?availableTime:0;
    }

}
