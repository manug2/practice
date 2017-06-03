package extras.queue;


import java.util.concurrent.*;

import static org.junit.Assert.fail;

public class QueuesTestHelper {

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

    public static void assertBlockedTryingToOffer(Queues.BlockingQueue mpq, int item) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Boolean> f = es.submit(new Offering(mpq, item));
        try {
            f.get(100, TimeUnit.MILLISECONDS);
            fail("offering should have blocked");
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {}
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

    public static void assertBlockedPolling(Queues.BlockingQueue mpq) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        final Future<Integer> f = es.submit(new Taker(mpq));
        try {
            f.get(100, TimeUnit.MILLISECONDS);
            fail("polling should have blocked");
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        } catch (TimeoutException e) {}
    }

}
