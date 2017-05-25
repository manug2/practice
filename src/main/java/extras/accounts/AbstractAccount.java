package extras.accounts;


public abstract class AbstractAccount {
    final int acNo;

    public AbstractAccount(int acNo) {
        this.acNo = acNo;
    }

    public abstract double balance();
}
