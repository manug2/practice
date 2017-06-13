package extras.queue.skip;


import extras.queue.Queues;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Math.random;


public class SkipQueue implements
        Queues.MinPriorityQueue, Queues.BlockingQueue {

    private final int capacity;
    private final ArrayList<Lane> lanes = new ArrayList<>();
    private final double probability=0.5;

    public SkipQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean put(int item) {
        try {
            offer(item);
            return true;
        } catch (InterruptedException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int take() {
        try {
            return poll();
        } catch (InterruptedException e){
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public void clear() {
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void offer(int item) throws InterruptedException {
        Stack<Node> stack = new Stack<>();
        Node predecessor = predecessor(item, stack);
        Node node1 = getLockedPredecessor(predecessor, item);
        Node node2 = node1.next;
        if (node2.item == item) {
            node1.lock.unlock();
            return;
        }

        if (insertUsingLockedPredecessor(node1, item, stack))
            return;


    }
    private boolean insertUsingLockedPredecessor(Node node1, int item, Stack<Node> stack) {
        Node pre = predecessor(item, stack);
        if (pre.item == item)
            return false;

        Node n = insert(pre, item);

        while (random() < probability) {
            Node up = (stack.size()>0)
                    ? insert(stack.pop(), item)
                    : insert(newLane().head, item);
            up.down = n;
            n.up = up;
            n = up;
        }

        return true;
    }

    private Node getLockedPredecessor(Node node1, int item) throws InterruptedException {
        node1.lock.lockInterruptibly();
        Node node2 = node1.next;
        while (node2.item < item) {
            node1.lock.unlock();
            node1 = node2;
            node1.lock.lockInterruptibly();
            node2 = node1.next;
        }
        return node1;
    }

    @Override
    public int poll() throws InterruptedException {
        while (! Thread.currentThread().isInterrupted()) {
            int item = extract_min();
            return item;
        }
        throw new InterruptedException("interrupted while polling");
    }

    public int extract_min() {
        Node head = lanes.get(0).head;
        if (head.next== Lane.NIL)
            return head.item;
        final int item = head.next.item;

        for (Lane lane : lanes) {
            head = lane.head;
            Node next = head.next;
            if (next != Lane.NIL && next.item == item) {
                head.next = next.next;
                if (next.next!= Lane.NIL)
                    next.next.left = head;
            } else {
                break;
            }
        }

        return item;
    }
    public int predecessor_key(int key, Stack<Node> stack) {
        return predecessor(key, stack).item;
    }

    public Node predecessor(int key, Stack<Node> stack) {
        Node pre = lanes.get(lanes.size()-1).head;
        while (pre.down != null){
            pre = predecessor(pre, key);
            stack.push(pre);
            pre = pre.down;
        }

        pre = predecessor(pre, key);
        if (pre.item == key)
            pre = pre.left;

        return pre;
    }

    private Node predecessor(Node from, int key) {
        if (from.item == key)
            return from;

        Node pre = from;
        Node current = pre.next;
        while (current!= Lane.NIL && current.item <= key) {
            pre = current;
            current = current.next;
        }
        return pre;
    }

    public boolean insert(int item) {

        Stack<Node> stack = new Stack<>();
        Node pre = predecessor(item, stack);
        if (pre.item == item)
            return false;

        Node n = insert(pre, item);

        while (random() < probability) {
            Node up = (stack.size()>0)
                    ? insert(stack.pop(), item)
                    : insert(newLane().head, item);
            up.down = n;
            n.up = up;
            n = up;
        }

        return true;
    }

    private Lane newLane() {
        final Lane prev = lanes.get(lanes.size()-1);
        Lane lane = new Lane(lanes.size());
        lanes.add(lane);

        prev.head.up = lane.head;
        lane.head.down = prev.head;
        return lane;
    }

    public Node insert(Node from, int item) {
        Node pre = predecessor(from, item);
        if (pre.next!= Lane.NIL && pre.next.item==item)
            return pre;

        Node n = new Node(item);
        n.next = pre.next;
        n.left = pre;

        if (pre.next!= Lane.NIL)
            pre.next.left = n;
        pre.next = n;

        return n;
    }


}
