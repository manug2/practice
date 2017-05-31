package extras.queue;

import org.junit.Test;

import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;


public class TestMinPriorityQueueSkipList {

    final MinPriorityQueueSkipList mpq = new MinPriorityQueueSkipList(10);

    @Test(expected = TimeoutException.class)
    public void should_throw_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        mpq.put(112, 100);
    }

    @Test(expected = TimeoutException.class)
    public void should_throw_underflow_error_when_empty() {
        mpq.put(112);
        mpq.take();
        mpq.take(100);
    }

    @Test(expected = TimeoutException.class)
    public void should_throw_underflow_error_when_made_empty() {
        mpq.take(100);
    }

}
