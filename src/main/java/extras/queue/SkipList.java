package extras.queue;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static java.lang.Math.random;


public class SkipList {
    private final int capacity;
    private int size=0;
    List<Lane> lanes;
    private double probability=0.5;

    public SkipList(int capacity) {
        this.capacity  = capacity;
        lanes = new ArrayList<>();
        lanes.add(new Lane(0));
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
        while (current!=null && current.item <= key) {
            pre = current;
            current = current.next;
        }
        return pre;
    }

    public boolean add(int item) {
        if (size==capacity)
            return false;

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

        size++;
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
        if (pre.next!=null && pre.next.item==item)
            return pre;

        Node n = new Node(item);
        n.next = pre.next;
        n.left = pre;

        if (pre.next!= null)
            pre.next.left = n;
        pre.next = n;

        return n;
    }

    public int size() {
        return capacity;
    }

    public int extract_min() {
        Node head = lanes.get(0).head;
        if (head.next==null)
            return head.item;
        final int item = head.next.item;

        for (Lane lane : lanes) {
            head = lane.head;
            Node next = head.next;
            if (next != null && next.item == item) {
                head.next = next.next;
                if (next.next!=null)
                    next.next.left = head;
            } else {
                break;
            }
        }

        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkipList(").append(System.lineSeparator());
        Iterator<Lane> iter = lanes.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next()).append(System.lineSeparator());
        }

        return sb.append(')').toString();
    }

    public boolean isFull() {
        return size==capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size=0;
        for (Lane lane: lanes)
            lane.head.next=null;
    }

    class Lane {
        private final int level;
        Node head = new Node(Integer.MIN_VALUE);
        public Lane(int num) {
            this.level = num;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Lane").append(level).append('[');
            Node current = head;

            while (current!=null) {
                if (current!=head)
                    sb.append(',').append(' ');

                sb.append(current.item);
                current = current.next;
            }

            return sb.append(']').toString();
        }
    }

    class Node {
        final int item;
        Node next, down;
        Node up, left;
        Node(int item)  {
            this.item = item;
        }
    }
}
