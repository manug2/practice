package extras.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import static extras.accounts.TransferTaskForTest.*;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class TestTransactionsWithState {

    @Parameterized.Parameters(name="{0}-{1}-{2}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[]{1, 1, 100});

        p.add(new Object[]{10, 1, 100});
        p.add(new Object[]{10, 2, 100});
        p.add(new Object[]{10, 4, 100});
        p.add(new Object[]{10, 1, 1000});
        p.add(new Object[]{10, 10, 1000});
        p.add(new Object[]{100, 10, 10_000});

        p.add(new Object[]{1, 1, 100_000});
        p.add(new Object[]{1, 1, 200_000});

        //        p.add(new Object[]{1, 1, 1000_000});
        return p;
    }

    public TestTransactionsWithState(int tasks, int threads, int numOfAccounts) {
        this.tasks = tasks;
        this.threads = threads;
        this.numOfAccounts = numOfAccounts;

        assert numOfAccounts / tasks > 0;
        assert numOfAccounts % tasks == 0;
    }

    final int tasks, threads, numOfAccounts;

    @Test public void should_issue_transfers_and_match_expected_balances() {

        AccountManager manager = new AccountManager(new TransactionManagerMT());
        int[] accounts = createAccounts(numOfAccounts, manager);

        ExecutorService es = Executors.newFixedThreadPool(threads);
        List<Future<?>> futs = new ArrayList<>();

        for (int i=0; i<tasks; i++) {
            final int start = i*(numOfAccounts/tasks);
            final int end = start + numOfAccounts/tasks;
            futs.add(es.submit(new TransferTaskForTest(accounts, manager, start, end)));
        }

        Iterator<Future<?>> fi = futs.iterator();
        try {
            while(fi.hasNext()) {
                fi.next().get(200, TimeUnit.MILLISECONDS);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
        }

        es.shutdown();

        double[] finalBalances = prepareExpectedBalances(accounts);
        assertExpectedBalances(manager, accounts, finalBalances);
    }
}

