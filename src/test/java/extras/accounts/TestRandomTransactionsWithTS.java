package extras.accounts;


import org.junit.Test;

import static java.lang.Math.floor;
import static java.lang.Math.random;
import static org.junit.Assert.assertEquals;

public class TestRandomTransactionsWithTS {

    @Test public void should_have_correct_state_after() {
        int from = randomInt(1000);
        int to = randomInt(1000);
        int amount = randomInt(1000);

        AccountManager<AccountWithTS> manager = new AccountManager<>(new TransactionManagerTS());
        manager.withAccount(from, 10000.00);
        manager.withAccount(to, 10000.00);
        manager.transfer(from, to, amount);

        assertEquals(10000.00 - amount, manager.balance(from), 0.0001);
        assertEquals(10000.00 + amount, manager.balance(to), 0.0001);
    }

    private int randomInt(int mod) {
        return ((Double) floor(random() * mod)).intValue();
    }
}
