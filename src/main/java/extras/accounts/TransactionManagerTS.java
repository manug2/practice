package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManagerTS implements TransactionManagerI<AccountWithTS> {
    AtomicInteger scn = new AtomicInteger(0);
    @Override
    public boolean transfer(double amount, AccountWithTS f, AccountWithTS t) {
        if (f.balance() < amount)
            return false;

        int T = scn.incrementAndGet();

        f.ts.set(T);
        t.ts.set(T);

        f.balance -= amount;
        t.balance += amount;

        return true;
    }

    @Override
    public AccountWithTS createAccount(int acNo, double initialBalance) {
        return new AccountWithTS(acNo, initialBalance);
    }
}
