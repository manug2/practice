package extras.queue;

import extras.queue.heap.MinPriorityQueueNoSync;
import extras.queue.heap.MinPriorityQueueSync;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue_CapacityBreach {

    final Queues.MinPriorityQueue mpq;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {new MinPriorityQueueNoSync(10)});
        p.add(new Object[] {new MinPriorityQueueSync(10)});
        return p;
    }
    @Before public void clearQ() {
        mpq.clear();
    }

    public TestMinPriorityQueue_CapacityBreach(Queues.MinPriorityQueue mpq) {
        this.mpq = mpq;
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

}
