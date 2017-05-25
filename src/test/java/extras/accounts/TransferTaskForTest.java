package extras.accounts;

import static extras.accounts.TransactionManagerI.Status.SUCCESS;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


class TransferTaskForTest implements Runnable {
    final int[] accounts;
    final AccountManager manager;
    final int start, end;

    TransferTaskForTest(int[] accounts, AccountManager manager, int start, int end) {
        this.accounts = accounts;
        this.manager = manager;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i=start; i<end; i++) {
            TransactionManagerI.Status s =
                    manager.transfer(accounts[i], accounts[i+1], (i+1)*100.00);
            if (SUCCESS != s)
                fail(format("txn [%s->%s, %s failed '%s']",
                        accounts[i], accounts[i+1], i*100.00, s.toString()));
        }
    }


    static int[] createAccounts(int numOfAccounts, AccountManager manager) {
        final double base_amount = numOfAccounts*1000.00;
        int[] accounts = new int[numOfAccounts+1];
        for (int i=0;i<accounts.length; i++) {
            accounts[i] = 1000_000 + i+1;
            manager.withAccount(accounts[i], base_amount);
        }
        return accounts;
    }

    static double[] prepareExpectedBalances(int[] accounts) {
        final double base_amount = (accounts.length-1)*1000.00;
        double[] finalBalances = new double[accounts.length];
        for (int i=0;i<accounts.length-1; i++) {
            finalBalances[i] = base_amount - 100.00;
        }
        finalBalances[accounts.length-1] = base_amount + (accounts.length-1)* 100.00;
        return finalBalances;
    }

    static void assertExpectedBalances(AccountManager manager, int[] accounts, double[] finalBalances) {
        for (int i=0; i<accounts.length; i++) {
            assertEquals(
                    format("wrong balance for account '%s'", accounts[i]),
                    finalBalances[i], manager.balance(accounts[i]), 0.0001);
        }
    }

}
