package extras.accounts;


public interface TransactionManagerI<T extends AbstractAccount> {
    boolean transfer(double amount, T f, T t);
    T createAccount(int acNo, double initialBalance);
}
