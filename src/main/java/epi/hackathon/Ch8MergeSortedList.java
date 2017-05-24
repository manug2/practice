package epi.hackathon;


public class Ch8MergeSortedList {
    public MyList merge(MyList list1, MyList list2) {
        System.out.println(list1);
        System.out.println(list2);

        MyList result = new MyList();
        MyListNode n1 = list1.head;
        MyListNode n2 = list2.head;

        while (n1!=null && n2!=null) {
            if (n1.item > n2.item) {
                n2 = result.add(n2);
            } else {
                n1 = result.add(n1);
            }
        }

        if (n1!=null) {
            while (n1 != null)
                n1 = result.add(n1);
        } else {
            while (n2 != null)
                n2 = result.add(n2);
        }

        return result;
    }

    static class MyList {
        MyListNode head;
        private void add(int i) {
            MyListNode n = new MyListNode(i);
            n.next = head;
            head = n;
        }

        public MyListNode add(MyListNode n) {
            MyListNode n_next = n.next;
            n.next = head;
            head = n;
            return n_next;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj==null)
                return false;
            if (this==obj)
                return true;
            if (! (obj instanceof  MyList))
                return false;
            MyList o = (MyList) obj;
            MyListNode n1 = head;
            MyListNode n2 = o.head;
            while (n1!=null && n2!=null) {
                if (! n1.equals(n2))
                    return false;

                n1 = n1.next;
                n2 = n2.next;
            }
            if (n1!=null || n2!=null)
                return false;
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
            sb.append('[');
            MyListNode n = head;
            while (n!=null) {
                sb.append(n.item).append(' ');
                n = n.next;
            }
            sb.append(']');
            return sb.toString();
        }
    }

    static class MyListNode {
        final int item;
        MyListNode next;

        MyListNode(int item) {
            this.item = item;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj==null)
                return false;
            if (this==obj)
                return true;
            if (! (obj instanceof  MyListNode))
                return false;
            MyListNode o = (MyListNode) obj;
            return item == o.item;
        }
    }

    static MyList fromReverseOf(int[] arr) {
        MyList list = new MyList();
        for (int i=arr.length; i>0; i--)
            list.add(arr[i-1]);
        return list;
    }

    static MyList from (int[] arr) {
        MyList list = new MyList();
        for (int a: arr)
            list.add(a);
        return list;
    }

}