package extras.queue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue {

    final Queues.MinPriorityQueue mpq;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {new MinPriorityQueueNoSync(10)});
        p.add(new Object[] {new MinPriorityQueueSync(10)});
        p.add(new Object[] {new MinPriorityQueueLock(10)});
        p.add(new Object[] {new MinPriorityQueueSkipList(10)});
        return p;
    }
    @Before public void clearQ() {
        mpq.clear();
    }

    public TestMinPriorityQueue(Queues.MinPriorityQueue mpq) {
        this.mpq = mpq;
    }

    @Test public void should_accept_items_till_capacity() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
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
