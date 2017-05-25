package extras.accounts;


import java.util.concurrent.atomic.AtomicInteger;

public class AccountWithTS extends AbstractAccount {
    double balance;
    AtomicInteger ts = new AtomicInteger(0);

    public AccountWithTS(int acNo, double balance) {
        super(acNo);
        this.balance = balance;
    }

    @Override
    public double balance() {
        return balance;
    }
}
