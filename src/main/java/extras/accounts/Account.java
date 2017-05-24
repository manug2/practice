package extras.accounts;


import java.util.concurrent.atomic.AtomicReference;

class Account {
    final int acNo;
    AtomicReference<AccountState> state;

    Account(int acNo, double balance) {
        this.acNo = acNo;
        this.state = new AtomicReference<>(new AccountState(balance, 0));
    }

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