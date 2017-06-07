package extras.queue;


import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSkipList {


    @Test public void should_become_full() {
        assertTrue(sl.isEmpty());
        sl.insert(200);
        sl.insert(100);
        sl.insert(500);
        sl.insert(300);
        sl.insert(400);
        sl.insert(1200);
        sl.insert(1100);
        sl.insert(1500);
        sl.insert(1300);
        sl.insert(1400);
        assertTrue(sl.isFull());
    }
    @Test public void should_be_able_to_add_and_extract_many_times() {
        sl.insert(200);
        sl.insert(100);
        sl.insert(500);
        sl.insert(300);
        sl.insert(400);
        sl.insert(1200);
        sl.insert(1100);
        sl.insert(1500);
        sl.insert(1300);
        sl.insert(1400);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
        assertEquals(300, sl.extract_min());
        assertEquals(400, sl.extract_min());
        assertEquals(500, sl.extract_min());
        assertEquals(1100, sl.extract_min());
        assertEquals(1200, sl.extract_min());
        assertEquals(1300, sl.extract_min());
        assertEquals(1400, sl.extract_min());
        assertEquals(1500, sl.extract_min());
    }

    @Test public void should_get_predecessor() {
        sl.insert(100);
        assertEquals(Integer.MIN_VALUE, sl.predecessor(100, new Stack<>()).item);
    }

    @Test public void should_get_predecessor_when_2_added() {
        sl.insert(100);
        sl.insert(300);
        assertEquals(100, sl.predecessor(300, new Stack<>()).item);
        assertEquals(Integer.MIN_VALUE, sl.predecessor(100, new Stack<>()).item);
    }

    @Test public void should_get_predecessor_when_2_added_in_reverse() {
        sl.insert(300);
        sl.insert(100);
        assertEquals(100, sl.predecessor(300, new Stack<>()).item);
        assertEquals(Integer.MIN_VALUE, sl.predecessor(100, new Stack<>()).item);
    }

    @Test public void should_be_able_to_extract_min_2_times_when_inserted_out_of_order() {
        sl.insert(200);
        sl.insert(100);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
    }

    @Test public void should_be_able_to_add_and_extract() {
        sl.insert(200);
        sl.insert(100);
        assertEquals(100, sl.extract_min());
        sl.insert(100);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
        sl.insert(500);
        sl.insert(300);
        sl.insert(400);
        assertEquals(300, sl.extract_min());
        assertEquals(400, sl.extract_min());
        assertEquals(500, sl.extract_min());
    }

    @Test public void should_be_able_to_extract_min_2_times() {
        sl.insert(100);
        sl.insert(200);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
    }

    @Test public void should_be_able_to_insert_2_items() {
        sl.insert(100);
        sl.insert(200);
    }

    @Test public void should_be_able_to_extract_min() {
        sl.insert(100);
        assertEquals(100, sl.extract_min());
    }

    @Test public void should_be_able_to_insert() {
        sl.insert(100);
    }

    SkipList sl = new SkipList(10);

}
