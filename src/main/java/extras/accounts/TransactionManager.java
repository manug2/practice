package extras.accounts;


public class TransactionManager implements TransactionManagerI {

    @Override
    public boolean transfer(double amount, Account f, Account t) {
        if (f.balance() < amount)
            return false;

        if (f.append(-amount)) {
            if (t.append(amount))
                return true;
            else {
                f.append(amount);
                return false;
            }
        }

        return false;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
