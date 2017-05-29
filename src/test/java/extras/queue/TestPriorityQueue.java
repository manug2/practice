package extras.queue;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class TestPriorityQueue {

    MinPriorityQueue mpq = new MinPriorityQueue(10);


    @Test public void should_accept_items_till_capacity() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        mpq.put(112);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_underflow_error_when_empty() {
        mpq.put(112);
        mpq.take();
        mpq.take();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_underflow_error_when_made_empty() {
        mpq.take();
    }

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
