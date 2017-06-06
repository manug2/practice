package extras.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static extras.queue.QueuesTestHelper.TEST_WAIT_TIMEOUT;
import static extras.queue.QueuesTestHelper.assertBlockedPolling;
import static extras.queue.QueuesTestHelper.assertBlockedTryingToOffer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue_Block_Performance {
    public static final long TEST_WAIT_TIMEOUT = 1000L;

    final Queues.BlockingQueue mpq;
    final int numOfMessages;
    ExecutorService es;

    public TestMinPriorityQueue_Block_Performance(
            int numOfMessages, Queues.BlockingQueue mpq) {
        this.mpq = mpq;
        this.numOfMessages = numOfMessages;
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {10_000, new MinPriorityQueueLock(10)});
        p.add(new Object[] {10_000, new MinPriorityQueueSkipList(10)});
        return p;
    }

    @Test public void should_offer_and_poll_expected_messages() {
        final int seed = new Double(Math.random()*1000).intValue();

        final Future<Map<Integer, Long>> of = startOffering(seed);
        final Future<Map<Integer, Long>> pf = startPolling();

        Map<Integer, Long> offers = new HashMap<>(1);
        collectOfferings(of, offers);

        Map<Integer, Long> polls = new HashMap<>(1);
        collectPolls(pf, polls);

        assertOfferings(seed, offers);
        assertPolls(seed, polls);
    }

    @Before public void setup() {
        mpq.clear();
        es = Executors.newFixedThreadPool(2);
    }

    @After public void shutDown() {
        es.shutdownNow();
    }

    public Future<Map<Integer, Long>> startOffering(final int seed) {
        Callable<Map<Integer, Long>> callable = new Callable<Map<Integer, Long>>() {
            @Override
            public Map<Integer, Long> call() throws Exception {
                Map<Integer, Long> countChecksumMap = new HashMap<>(1);
                int count=0;
                long cksum = 0L;
                for (int i=0; i< numOfMessages; i++) {
                    if (Thread.currentThread().isInterrupted())
                        break;

                    final int num = seed+i;
                    mpq.offer(num);
                    ++count;
                    cksum += num;
                }
                countChecksumMap.put(count, cksum);
                return countChecksumMap;
            }
        };

        return es.submit(callable);
    }

    public void collectOfferings(final Future<Map<Integer, Long>> f, final Map<Integer, Long> offers) {
        try {
            offers.putAll(
                    f.get(TEST_WAIT_TIMEOUT, TimeUnit.MILLISECONDS));

        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
            fail("offering time out");
        }
    }

    public void assertOfferings(final int seed, final Map<Integer, Long> offers) {

            assertEquals(1, offers.size());
            final int count = offers.keySet().iterator().next();

            assertEquals(numOfMessages, count);
            assertEquals(
                    offers.get(count),
                    (Long)(1L*(seed+count)*(seed+count+1)/2));
    }

    public Future<Map<Integer, Long>> startPolling() {
        Callable<Map<Integer, Long>> callable = new Callable<Map<Integer, Long>>() {
            @Override
            public Map<Integer, Long> call() throws Exception {
                Map<Integer, Long> countChecksumMap = new HashMap<>(1);
                int count=0;
                long cksum = 0L;
                int taken;
                do {
                    if (Thread.currentThread().isInterrupted())
                        break;

                    taken = mpq.poll();
                    ++count;
                    cksum += taken;
                } while (taken>-1);
                countChecksumMap.put(count, cksum);
                return countChecksumMap;
            }
        };

        return es.submit(callable);
    }

    public void collectPolls(Future<Map<Integer, Long>> f, Map<Integer, Long> polls) {
        try {
            polls.putAll(
                f.get(TEST_WAIT_TIMEOUT, TimeUnit.MILLISECONDS));

        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
            fail("polling time out");
        }
    }

    public void assertPolls(final int seed, final Map<Integer, Long> polls) {
            assertEquals(1, polls.size());
            final int count = polls.keySet().iterator().next();

            assertEquals(numOfMessages, count);
            assertEquals(
                    polls.get(count),
                    (Long)(1L*(seed+count)*(seed+count+1)/2));
    }

}
