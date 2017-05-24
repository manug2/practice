package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public class TransactionManagerMT implements TransactionManagerI {

    final int TRIES, TRIES_ROLLBACK;
    final AtomicInteger scn = new AtomicInteger(0);

    public TransactionManagerMT() {
        TRIES = 20;
        TRIES_ROLLBACK = 10;
    }

    @Override
    public boolean transfer(double amount, Account from, Account to) {
        int i=0;
        int T;
        AccountState fState, tState, nfs, nts;
        while (++i < TRIES) {
            T = scn.incrementAndGet();

            fState = from.state.get();
            tState = to.state.get();

            while (T < fState.ts || T < tState.ts) {
                Thread.yield();//Let other threads proceed and reduce contention
                T = scn.incrementAndGet();
                fState = from.state.get();
                tState = to.state.get();
            }

            nfs = new AccountState(fState.balance - amount, T);
            nts = new AccountState(tState.balance + amount, T);

            if (nfs.balance<0 || nts.balance < 0) {
                //Thread.yield();//Let other threads add some cash
                continue;
            }

            if (from.state.compareAndSet(fState, nfs)) {
                if (to.state.compareAndSet(tState, nts)) {
                    return true;
                } else {
                    if (! rollback(from, amount))
                        throw new IllegalStateException(
                                format("account '%s' has been wrongly debited with '%s'", from.acNo, amount));
                }
            }
        }

        return false;
    }

    private boolean rollback(Account from, double amount) {
        AccountState fState, nfs;
        int i=0;

        while (++i < TRIES_ROLLBACK) {
            fState = from.state.get();
            nfs = new AccountState(fState.balance + amount, scn.incrementAndGet());

            if (from.state.compareAndSet(fState, nfs))
                return true;
        }

        return false;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
