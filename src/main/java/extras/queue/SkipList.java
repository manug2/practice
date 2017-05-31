package extras.queue;


public class SkipList {
    private final List list;

    public SkipList(int capacity) {
        list = new List(capacity);
    }

    public void add(int item) {
        list.insert(item);
    }

    public int extract_min() {
        return list.extract();
    }

    class List {
        final static int HEAD_ITEM = -1;
        Node head = new Node(HEAD_ITEM);
        private final int capacity;
        private int size=0;

        public List(int capacity) {
            this.capacity  = capacity;
        }

        boolean insert(int item) {
            if (size==capacity)
                return false;
            Node predecessor = head;
            Node current = head.next;
            while (current != null && current.item < item) {
                predecessor = current;
                current = current.next;
            }
            Node n = new Node(item);
            n.next = predecessor.next;
            predecessor.next = n;
            size++;
            return true;
        }

        public int size() {
            return capacity;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Node current = head;

            while (current!=null) {

                if (current!=head)
                    sb.append(',').append(' ');

                sb.append(current.item);
                current = current.next;
            }

            return sb.append(']').toString();
        }

        public int extract() {
            Node current = head.next;
            if (current==null)
                return HEAD_ITEM;

            int item = current.item;
            head.next = current.next;
            current.next=null;
            return item;
        }
    }

    class Node {
        final int item;
        Node next;
        Node(int item)  {
            this.item = item;
        }
    }
}
