package extras.accounts;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class AccountManager<T extends AbstractAccount> {
    private final Map<Integer, T> accounts;
    private final TransactionManagerI<T> txnManager;

    public AccountManager(TransactionManagerI txnManager) {
        accounts = new HashMap<>();
        this.txnManager = txnManager;
    }

    public AccountManager withAccount(int acNo, double initBalance) {
        if (accounts.containsKey(acNo))
            throw new RuntimeException(
                    format("account '%s' already initialized with balance '%s'", acNo, initBalance));

        this.accounts.put(acNo, txnManager.createAccount(acNo, initBalance));
        return this;
    }

    public boolean transfer(int from, int to, double amount) {
        if (amount < 0.0)
            throw new IllegalArgumentException("Amount is negative");

        if (! accounts.containsKey(from))
            return false;
        final T f = accounts.get(from);

        if (! accounts.containsKey(to))
            return false;
        final T t = accounts.get(to);

        if (amount==0.0)
            return true;

        return txnManager.transfer(amount, f, t);
    }

    public double balance(int acNo) {
        if (accounts.containsKey(acNo))
            return accounts.get(acNo).balance();
        else
            return 0.0;
    }

}
