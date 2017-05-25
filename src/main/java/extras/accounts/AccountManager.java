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

    public TransactionManagerI.Status transfer(int from, int to, double amount) {
        if (from==to)
            return TransactionManagerI.Status.INVALID;

        if (! accounts.containsKey(from))
            return TransactionManagerI.Status.NO_ACCOUNT;
        final T f = accounts.get(from);

        if (! accounts.containsKey(to))
            return TransactionManagerI.Status.NO_ACCOUNT;
        final T t = accounts.get(to);

        if (amount==0.0)
            return TransactionManagerI.Status.SUCCESS;

        return txnManager.transfer(amount, f, t);
    }

    public double balance(int acNo) {
        if (accounts.containsKey(acNo))
            return accounts.get(acNo).balance();
        else
            return 0.0;
    }

}
