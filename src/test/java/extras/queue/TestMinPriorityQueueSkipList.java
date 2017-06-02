package extras.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class TestMinPriorityQueueSkipList {

    MinPriorityQueueSkipList mpq;

    @Before public void setup() {
        mpq = new MinPriorityQueueSkipList(10);
    }

    @Test
    public void should_throw_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        assertFalse(mpq.put(111, 100));
    }

    @Test
    public void should_throw_underflow_error_when_empty() {
        mpq.put(112);
        mpq.take();
        assertTrue(Integer.MIN_VALUE == mpq.take(100));
    }

    @Test
    public void should_throw_underflow_error_when_taking_from_empty() {
        assertNull(mpq.take(100));
    }

}
