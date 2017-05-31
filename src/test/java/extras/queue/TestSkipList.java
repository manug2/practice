package extras.queue;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSkipList {

    @Test public void should_be_able_to_extract_min_2_times_when_inserted_out_of_order() {
        sl.add(200);
        sl.add(100);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
    }

    @Test public void should_be_able_to_add_and_extract() {
        sl.add(200);
        sl.add(100);
        assertEquals(100, sl.extract_min());
        sl.add(100);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
        sl.add(500);
        sl.add(300);
        sl.add(400);
        assertEquals(300, sl.extract_min());
        assertEquals(400, sl.extract_min());
        assertEquals(500, sl.extract_min());
    }

    @Test public void should_be_able_to_extract_min_2_times() {
        sl.add(100);
        sl.add(200);
        assertEquals(100, sl.extract_min());
        assertEquals(200, sl.extract_min());
    }

    @Test public void should_be_able_to_insert_2_items() {
        sl.add(100);
        sl.add(200);
    }

    @Test public void should_be_able_to_extract_min() {
        sl.add(100);
        assertEquals(100, sl.extract_min());
    }

    @Test public void should_be_able_to_insert() {
        sl.add(100);
    }

    SkipList sl = new SkipList(10);

}
