package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManagerMT implements TransactionManagerI {

    final int TRIES;
    final AtomicInteger scn = new AtomicInteger(0);

    public TransactionManagerMT() {
        TRIES = 20;
    }

    @Override
    public boolean transfer(double amount, Account from, Account to) {
        int i=0;
        int T;
        AccountState fState, tState;
        while (++i < TRIES) {
            T = scn.incrementAndGet();
            fState = from.state.get();
            tState = to.state.get();
            AccountState nfs = new AccountState(fState.balance - amount, T);
            AccountState nts = new AccountState(tState.balance + amount, T);

            if (nfs.balance<0 || nts.balance < 0)
                continue;

            while (T < fState.ts || T < tState.ts) {
                T = scn.incrementAndGet();
                fState = from.state.get();
                tState = to.state.get();
            }

            if (from.state.compareAndSet(fState, nfs)) {
                if (to.state.compareAndSet(tState, nts))
                    return true;
                else {
                    from.state.set(fState);
                    return false;
                }
            }
        }

        return false;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
