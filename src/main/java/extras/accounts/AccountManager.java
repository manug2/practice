package extras.accounts;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class AccountManager {
    private final Map<Integer, Account> accounts;

    public AccountManager() {
        accounts = new HashMap<>();
    }

    public AccountManager withAccount(int acNo, double initBalance) {
        if (accounts.containsKey(acNo))
            throw new RuntimeException(
                    format("account '%s' already initialized with balance '%s'", acNo, initBalance));

        this.accounts.put(acNo, new Account(acNo, initBalance));
        return this;
    }

    public boolean transfer(int from, int to, double amount) {
        if (! accounts.containsKey(from))
            return false;
        final Account f = accounts.get(from);

        if (! accounts.containsKey(to))
            return false;
        final Account t = accounts.get(to);

        return _transfer(amount, f, t);
    }

    private boolean _transfer(double amount, Account f, Account t) {
        if (f.balance < amount)
            return false;

        f.balance -= amount;
        t.balance += amount;

        return true;
    }

    public double balance(int acNo) {
        if (accounts.containsKey(acNo))
            return accounts.get(acNo).balance;
        else
            return 0.0;
    }

}

