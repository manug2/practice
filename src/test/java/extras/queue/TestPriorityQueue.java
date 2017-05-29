package extras.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPriorityQueue {

    MinPriorityQueue mpq = new MinPriorityQueue(10);

    @Test public void should_put_in_Q() {
        mpq.put(102);
    }

    @Test public void should_take_from_Q() {
        mpq.put(102);
        assertEquals(102, mpq.take());
    }

    @Test public void should_take_min_item_from_Q_when_at_top() {
        mpq.put(102);
        mpq.put(103);
        assertEquals(102, mpq.take());
    }

    @Test public void should_take_min_item_from_Q_when_not_at_top() {
        mpq.put(103);
        mpq.put(102);
        assertEquals(102, mpq.take());
    }

}
