package extras.accounts;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class TestAccountTransactions {

    public TestAccountTransactions(TransactionManagerI txnMgr) {
        manager = new AccountManager(txnMgr);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> params() {
        List<Object[]> p = new ArrayList<>();
        p.add(new Object [] {new TransactionManager()});
        p.add(new Object [] {new TransactionManagerMT()});
        return p;
    }

    final private AccountManager manager;

    @Test public void should_not_transfer_when_low_balance() {
        manager
                .withAccount(10001, 100.00)
                .withAccount(10002, 123.00);


        assertFalse(manager.transfer(10001, 10002, 1000.00));

        assertEquals(100.0, manager.balance(10001), 0.0001);
        assertEquals(123.0, manager.balance(10002), 0.0001);
    }

    @Test public void should_transfer_from_one_account_to_another() {
        manager
                .withAccount(10001, 100.00)
                .withAccount(10002, 123.00);

        manager.transfer(10001, 10002, 10.00);
        assertEquals(90.0, manager.balance(10001), 0.0001);
        assertEquals(133.0, manager.balance(10002), 0.0001);
    }

}
