package extras.accounts;


import java.util.concurrent.atomic.AtomicReference;

class AccountWithState extends AbstractAccount {
    AtomicReference<AccountState> state;

    AccountWithState(int acNo, double balance) {
        super(acNo);
        this.state = new AtomicReference<>(new AccountState(balance, 0));
    }

    @Override
    public double balance() {
        return state.get().balance;
    }

    public boolean append(double amount) {
        AccountState old = state.get();
        AccountState newAS = new AccountState(old.balance + amount, old.ts);
        return state.compareAndSet(old, newAS);
    }
}

class AccountState {
    final double balance;
    final int ts;

    AccountState(double balance, int ts) {
        this.balance = balance;
        this.ts = ts;
    }
}