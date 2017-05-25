package extras.accounts;


public class TransactionManager implements TransactionManagerI<SimpleAccount> {

    @Override
    public Status transfer(double amount, SimpleAccount f, SimpleAccount t) {
        if (f.balance < amount)
            return Status.LOW_BALANCE;

        f.balance -= amount;
        t.balance += amount;

        return Status.SUCCESS;
    }

    @Override
    public SimpleAccount createAccount(int acNo, double initialBalance) {
        return new SimpleAccount(acNo, initialBalance);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
