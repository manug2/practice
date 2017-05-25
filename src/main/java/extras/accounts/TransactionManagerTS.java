package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManagerTS implements TransactionManagerI<AccountWithTS> {
    AtomicInteger scn = new AtomicInteger(0);
    final int TRIES = 20;
    @Override
    public Status transfer(double amount, AccountWithTS f, AccountWithTS t) {
        //order accounts in ascending order
        if (f.acNo > t.acNo)
            return transfer(-amount, t, f);

        int i=0;
        while (++i < TRIES) {
            int T = scn.incrementAndGet();
            int fts = f.ts.get();
            int tts = t.ts.get();

            if (T < fts || T < tts) {
                continue;
            }

            if (! f.ts.compareAndSet(fts, Integer.MAX_VALUE)) {
                continue;
            }

            if (! t.ts.compareAndSet(tts, Integer.MAX_VALUE)) {
                f.ts.set(fts);
                Thread.yield();
                continue;
            }

            if ((f.balance - amount < -0.0001)
                    || (t.balance + amount < -0.0001)) {
                f.ts.set(fts);
                t.ts.set(tts);
                return Status.LOW_BALANCE;
            }

            f.balance -= amount;
            t.balance += amount;

            f.ts.set(T);
            t.ts.set(T);

            return Status.SUCCESS;
        }

        return Status.FAILED;
    }

    @Override
    public AccountWithTS createAccount(int acNo, double initialBalance) {
        return new AccountWithTS(acNo, initialBalance);
    }
}
