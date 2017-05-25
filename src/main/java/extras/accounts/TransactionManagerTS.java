package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManagerTS implements TransactionManagerI<AccountWithTS> {
    AtomicInteger scn = new AtomicInteger(0);
    final int TRIES = 2;
    @Override
    public boolean transfer(double amount, AccountWithTS f, AccountWithTS t) {
        if (f.balance + amount < -0.0001)
            return false;

        //order account in ascending order
        if (f.acNo > t.acNo)
            return transfer(-amount, t, f);

        int i=0;
        while (++i < TRIES) {
            int T = scn.incrementAndGet();
            int fts = f.ts.get();
            int tts = t.ts.get();

            while (T < fts || T < tts) {
                T = scn.incrementAndGet();
                fts = f.ts.get();
                tts = t.ts.get();
            }

            if (!f.ts.compareAndSet(fts, Integer.MAX_VALUE)) {
                //Thread.yield();
                continue;
            }

            if (!t.ts.compareAndSet(tts, Integer.MAX_VALUE)) {
                f.ts.set(fts);
                Thread.yield();
                continue;
            }

            if (f.balance + amount < -0.0001) {
                f.ts.set(fts);
                t.ts.set(tts);
                return false;
            }

            f.balance -= amount;
            t.balance += amount;

            f.ts.set(T);
            t.ts.set(T);

            return true;
        }

        return false;
    }

    @Override
    public AccountWithTS createAccount(int acNo, double initialBalance) {
        return new AccountWithTS(acNo, initialBalance);
    }
}
