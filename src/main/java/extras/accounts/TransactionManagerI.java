package extras.accounts;


public interface TransactionManagerI<T extends AbstractAccount> {
    Status transfer(double amount, T f, T t);
    T createAccount(int acNo, double initialBalance);

    enum Status{SUCCESS, FAILED, LOW_BALANCE, NO_ACCOUNT}
}
