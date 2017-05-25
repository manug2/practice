package extras.accounts;


public class SimpleAccount extends AbstractAccount {
    double balance;

    public SimpleAccount(int acNo, double balance) {
        super(acNo);
        this.balance = balance;
    }

    @Override
    public double balance() {
        return balance;
    }
}
