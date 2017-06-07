package extras.queue;

import extras.queue.heap.MinPriorityQueueLock;
import extras.queue.heap.skilList.MinPriorityQueueSkipList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static extras.queue.QueuesTestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue_Block {

    final Queues.BlockingQueue mpq;

    public TestMinPriorityQueue_Block(Queues.BlockingQueue mpq) {
        this.mpq = mpq;
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {new MinPriorityQueueLock(10)});
        p.add(new Object[] {new MinPriorityQueueSkipList(10)});
        return p;
    }

    @Before public void setup() {
        mpq.clear();
    }

    @Test
    public void should_block_offer_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        assertBlockedTryingToOffer(mpq, 111);
    }

    @Test
    public void should_block_poll_error_when_empty() {
        mpq.put(112);
        mpq.take();
        assertBlockedPolling(mpq);
    }

    @Test
    public void should_throw_underflow_error_when_made_empty() {
        assertBlockedPolling(mpq);
    }

    @Test
    public void should_throw_collect_items_in_order() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        IntStream.range(1, 11).forEach(x -> assertEquals(x, mpq.take()));
    }

}
