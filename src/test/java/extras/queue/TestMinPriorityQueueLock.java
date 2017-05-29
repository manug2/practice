package extras.queue;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class TestMinPriorityQueueLock {

    final MinPriorityQueueLock mpq = new MinPriorityQueueLock(10);

    @Test
    public void should_throw_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        assertFalse(mpq.put(112, 100));
    }

    @Test
    public void should_throw_underflow_error_when_empty() {
        mpq.put(112);
        mpq.take();
        assertNull(mpq.take(100));
    }

    @Test
    public void should_throw_underflow_error_when_made_empty() {
        assertNull(mpq.take(100));
    }

}
