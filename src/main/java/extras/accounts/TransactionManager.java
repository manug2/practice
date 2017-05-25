package extras.accounts;


public class TransactionManager implements TransactionManagerI<SimpleAccount> {

    @Override
    public boolean transfer(double amount, SimpleAccount f, SimpleAccount t) {
        if (f.balance() < amount)
            return false;

        f.balance -= amount;
        t.balance += amount;

        return true;
    }

    @Override
    public SimpleAccount createAccount(int acNo, double initialBalance) {
        return new SimpleAccount(acNo, initialBalance);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
