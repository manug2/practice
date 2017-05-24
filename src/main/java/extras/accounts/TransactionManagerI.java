package extras.accounts;


public interface TransactionManagerI {
    boolean transfer(double amount, Account f, Account t);
}
