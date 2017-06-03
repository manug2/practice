package extras.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static extras.queue.QueuesTestHelper.assertBlockedPolling;
import static extras.queue.QueuesTestHelper.assertBlockedTryingToOffer;


public class TestMinPriorityQueueSkipList {

    MinPriorityQueueSkipList mpq;

    @Before public void setup() {
        mpq = new MinPriorityQueueSkipList(10);
    }

    @Test
    public void should_throw_overflow_error_when_full() {
        IntStream.range(1, 11).forEach(x -> mpq.put(x));
        assertBlockedTryingToOffer(mpq, 111);
    }

    @Test
    public void should_throw_underflow_error_when_empty() {
        mpq.put(112);
        mpq.take();
        assertBlockedPolling(mpq);
    }

    @Test
    public void should_throw_underflow_error_when_taking_from_empty() {
        assertBlockedPolling(mpq);
    }

}
