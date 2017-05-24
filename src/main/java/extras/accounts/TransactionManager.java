package extras.accounts;


public class TransactionManager {

    public boolean transfer(double amount, Account f, Account t) {
        if (f.balance < amount)
            return false;

        f.balance -= amount;
        t.balance += amount;

        return true;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
