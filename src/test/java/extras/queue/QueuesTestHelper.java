package extras.queue;


import java.util.concurrent.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class QueuesTestHelper {

    public static final int TEST_WAIT_TIMEOUT = 50;

    public static void assertBlockedTryingToOffer(Queues.BlockingQueue mpq, int item) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Boolean> f = es.submit(new Offering(mpq, item));
        try {
            f.get(TEST_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
            fail("offering should have blocked");
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
        } finally {
            es.shutdownNow();
        }
    }

    public static void assertTimeOutTryingToOffer(Queues.TimedBlockingQueue mpq, int item, int timeout) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Boolean> f = es.submit(new TimedOffering(mpq, item, timeout));
        try {
            assertFalse(f.get(TEST_WAIT_TIMEOUT + timeout, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            es.shutdownNow();
        }
    }

    public static void assertBlockedPolling(Queues.BlockingQueue mpq) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Integer> f = es.submit(new Taker(mpq));
        try {
            f.get(TEST_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
            fail("polling should have blocked");
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {
        } finally {
            es.shutdownNow();
        }
    }

    public static void assertTimedPolling(Queues.TimedBlockingQueue mpq, int timeout) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Integer> f = es.submit(new TimedTaker(mpq, timeout));
        try {
            assertTrue(
                    Integer.MIN_VALUE ==
                    f.get(TEST_WAIT_TIMEOUT + timeout, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (TimeoutException e) {
            f.cancel(true);
            fail("polling timed out");
        } finally {
            es.shutdownNow();
        }
    }


    static class Offering implements Callable<Boolean> {
        final Queues.BlockingQueue mpq;
        final int item;
        Offering(Queues.BlockingQueue mpq, int item) {
            this.mpq = mpq;
            this.item = item;
        }

        @Override
        public Boolean call() throws Exception {
            mpq.offer(item);
            return true;
        }
    }

    static class TimedOffering implements Callable<Boolean> {
        final Queues.TimedBlockingQueue mpq;
        final int item, timeout;
        TimedOffering(Queues.TimedBlockingQueue mpq, int item, int timeout) {
            this.mpq = mpq;
            this.item = item;
            this.timeout = timeout;
        }

        @Override
        public Boolean call() throws Exception {
            return mpq.offer(item, timeout);
        }
    }

    static class Taker implements Callable<Integer> {
        final Queues.BlockingQueue mpq;
        Taker(Queues.BlockingQueue mpq) {
            this.mpq = mpq;
        }

        @Override
        public Integer call() throws Exception {
            return mpq.poll();
        }
    }

    static class TimedTaker implements Callable<Integer> {
        final Queues.TimedBlockingQueue mpq;
        final int timeout;
        TimedTaker(Queues.TimedBlockingQueue mpq, int timeout) {
            this.mpq = mpq;
            this.timeout = timeout;
        }

        @Override
        public Integer call() throws Exception {
            return mpq.poll(timeout);
        }
    }

}
