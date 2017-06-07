package extras.queue;

import extras.queue.heap.MinPriorityQueueLock;
import extras.queue.skip.MinPriorityQueueSkipList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static extras.queue.QueuesTestHelper.*;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue_BlockTimeout {

    final Queues.TimedBlockingQueue mpq;

    public TestMinPriorityQueue_BlockTimeout(Queues.TimedBlockingQueue mpq) {
        this.mpq = mpq;
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {new MinPriorityQueueLock(10)});
        p.add(new Object[] {new MinPriorityQueueSkipList(10)});
        return p;
    }

    @Test
    public void should_throw_underflow_error_when_made_empty() {
        assertEquals(Integer.MIN_VALUE, mpq.take());
    }

    @Test
    public void should_throw_collect_items_in_order() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        IntStream.range(1, 11).forEach(x -> assertEquals(x, mpq.take()));
    }

    @Test
    public void should_block_offer_with_timout_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        assertTimeOutTryingToOffer(mpq, 111, 50);
    }

    @Test
    public void should_block_poll_with_timeout_when_empty() {
        assertTimedPolling(mpq, 50);
    }

}
