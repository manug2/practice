package extras.queue;

import extras.queue.heap.MinPriorityQueueLock;
import extras.queue.heap.skilList.MinPriorityQueueSkipList;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class TestMinPriorityQueue_Block_Performance {
    public static final long TEST_WAIT_TIMEOUT = 1000L;
    private static final int POISON_PILL = Integer.MAX_VALUE;

    final Queues.BlockingQueue mpq;
    final int numOfMessages;
    ExecutorService es;

    public TestMinPriorityQueue_Block_Performance(
            int numOfMessages, Queues.BlockingQueue mpq) {
        this.mpq = mpq;
        this.numOfMessages = numOfMessages;
    }

    @Parameterized.Parameters(name = "{0}-{1}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[] {500_000, new MinPriorityQueueLock(10)});
        p.add(new Object[] {500_000, new MinPriorityQueueSkipList(10)});
        return p;
    }

    @Test public void should_offer_and_poll_expected_messages() {
        final int seed = new Double(Math.random()*1000).intValue();

        final Future<Map<Integer, Long>> of = startOffering(seed);
        final Future<Map<Integer, Long>> pf = startPolling();

        Map<Integer, Long> offers = new HashMap<>(1);
        collectOfferings(of, offers);
        poisonConsumer();

        Map<Integer, Long> polls = new HashMap<>(1);
        collectPolls(pf, polls);

        es.shutdownNow();

        assertOfferings(seed, offers);
        assertPolls(polls, offers.values().iterator().next());
    }

    private void poisonConsumer() {
        try {
            mpq.offer(POISON_PILL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before public void setup() {
        mpq.clear();
        es = Executors.newFixedThreadPool(2);
    }

    @After public void shutDown() {

    }

    public Future<Map<Integer, Long>> startOffering(final int seed) {
        Callable<Map<Integer, Long>> callable = new Callable<Map<Integer, Long>>() {
            @Override
            public Map<Integer, Long> call() throws InterruptedException {

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

        } catch (ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
            fail("offering time out");
        } catch (InterruptedException e) {
            System.out.println("collectOfferings got interrupted");
        }
    }

    public void assertOfferings(final int seed, final Map<Integer, Long> offers) {

            assertEquals(1, offers.size());
            final int count = offers.keySet().iterator().next();

            assertEquals(numOfMessages, count);
            Long cksum = 0L;
            for (int i=0; i<numOfMessages; i++)
                cksum += seed + i;
            assertEquals(
                    offers.get(count), cksum);
    }

    public Future<Map<Integer, Long>> startPolling() {
        Callable<Map<Integer, Long>> callable = new Callable<Map<Integer, Long>>() {
            @Override
            public Map<Integer, Long> call() throws InterruptedException {

                Map<Integer, Long> countChecksumMap = new HashMap<>(1);
                int count=0;
                long cksum = 0L;
                int taken = 0;
                while(taken != POISON_PILL) {
                    ++count;
                    cksum += taken;

                    if (Thread.currentThread().isInterrupted())
                        break;
                    taken = mpq.poll();
                }
                countChecksumMap.put(count-1, cksum);
                return countChecksumMap;
            }
        };

        return es.submit(callable);
    }

    public void collectPolls(Future<Map<Integer, Long>> f, Map<Integer, Long> polls) {
        try {
            polls.putAll(
                f.get(TEST_WAIT_TIMEOUT, TimeUnit.MILLISECONDS));

        } catch (ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
            fail("polling timed out");
        } catch (InterruptedException e) {
            System.out.println("collectPolls got interrupted");
        }
    }

    public void assertPolls(final Map<Integer, Long> polls, final Long cksum) {
            assertEquals(1, polls.size());
            final int count = polls.keySet().iterator().next();

            assertEquals(numOfMessages, count);
            assertEquals(cksum, polls.get(count));
    }

}
