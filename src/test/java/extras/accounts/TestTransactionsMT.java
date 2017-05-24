package extras.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class TestTransactionsMT {

    @Parameterized.Parameters(name="{0}-{1}-{2}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object[]{10, 1, 100});
        p.add(new Object[]{10, 2, 100});
        p.add(new Object[]{10, 4, 100});
        p.add(new Object[]{10, 1, 1000});
        p.add(new Object[]{10, 10, 1000});
        return p;
    }

    public TestTransactionsMT(int tasks, int threads, int numOfAccounts) {
        this.tasks = tasks;
        this.threads = threads;
        this.numOfAccounts = numOfAccounts;

        assert numOfAccounts / tasks > 0;
        assert numOfAccounts % tasks == 0;
    }

    final int tasks, threads, numOfAccounts;

    @Test public void should_issue_transfers_and_match_expected_balances() {

        AccountManager manager = new AccountManager(new TransactionManagerMT());
        int[] accounts = createAccounts(manager);

        ExecutorService es = Executors.newFixedThreadPool(threads);
        List<Future<?>> futs = new ArrayList<>();

        for (int i=0; i<tasks; i++) {
            final int start = i*(numOfAccounts/tasks);
            final int end = start + numOfAccounts/tasks;
            futs.add(es.submit(new TransferTask(accounts, manager, start, end)));
        }

        Iterator<Future<?>> fi = futs.iterator();
        while(fi.hasNext()) {
            try {
                fi.next().get(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        es.shutdownNow();

        double[] finalBalances = prepareExpectedBalances(accounts);
        assertExpectedBalances(manager, accounts, finalBalances);
    }

    private int[] createAccounts(AccountManager manager) {
        int[] accounts = new int[numOfAccounts+1];
        for (int i=0;i<accounts.length; i++) {
            accounts[i] = 10_000 + i+1;
            manager.withAccount(accounts[i], 100_000.00);
        }
        return accounts;
    }

    private double[] prepareExpectedBalances(int[] accounts) {
        double[] finalBalances = new double[accounts.length];
        for (int i=0;i<accounts.length-1; i++) {
            finalBalances[i] = 100_000.00 - 100.00;
        }
        finalBalances[accounts.length-1] = 100000.00 + (accounts.length-1)* 100.00;
        return finalBalances;
    }

    private void assertExpectedBalances(AccountManager manager, int[] accounts, double[] finalBalances) {
        for (int i=0; i<accounts.length; i++) {
            assertEquals(
                    format("wrong balance for account '%s'", accounts[i]),
                    finalBalances[i], manager.balance(accounts[i]), 0.0001);
        }
    }
}

class TransferTask implements Runnable {
    final int[] accounts;
    final AccountManager manager;
    final int start, end;

    TransferTask(int[] accounts, AccountManager manager, int start, int end) {
        this.accounts = accounts;
        this.manager = manager;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i=start; i<end; i++) {
            if (!manager.transfer(accounts[i], accounts[i+1], (i+1)*100.00))
                fail(format("txn [%s->%s, %s failed", accounts[i], accounts[i+1], i*100.00));
        }
    }

}
