package extras.queue;


import epi.hackathon.MinHeap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MinPriorityQueueLock implements MinPriorityQueue, BlockingMinPriorityQueue {

    private final MinHeap heap;
    private final ReentrantLock lock;
    private final Condition notFull, notEmpty;

    public MinPriorityQueueLock(int capacity) {
        heap = new MinHeap(capacity);
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    @Override
    public void put(int item) {
        try {
            lock.lockInterruptibly();
            while (true) {
                if (heap.isFull()) {
                    notFull.await();
                } else {
                    heap.insert(item);
                    notEmpty.signalAll();
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("queue is full");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int take() {
        try {
            lock.lockInterruptibly();
            while (true) {
                if (heap.isEmpty()) {
                    notEmpty.await();
                } else {
                    int item = heap.extract_min();
                    notFull.signalAll();
                    return item;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("queue is empty");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lockInterruptibly();
            if(! heap.isEmpty())
                heap.clear();
            notFull.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException("error clearing queue");
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean put(int item, int timeoutMS) {
        boolean didLock = false;
        try {
            didLock = lock.tryLock(timeoutMS, TimeUnit.MILLISECONDS);
            if (didLock) {
                if (heap.isFull())
                    return false;
                else {
                    heap.insert(item);
                    return true;
                }
            } else
                return false;
        } catch (InterruptedException e) {
            return false;
        } finally {
            if (didLock)
                lock.unlock();
        }
    }

    @Override
    public Integer take(int timeoutMS) {
        boolean didLock = false;
        try {
            didLock = lock.tryLock(timeoutMS, TimeUnit.MILLISECONDS);
            if (didLock) {
                if (heap.isEmpty())
                    return null;
                else
                    return heap.extract_min();
            } else
                return null;
        } catch (InterruptedException e) {
            return null;
        } finally {
            if (didLock)
                lock.unlock();
        }
    }

}
