/**
 * Represents a bank account with basic operations like deposit, withdraw, and balance check.
 */
public class BankAccount {
    private double balance;

    /**
     * Constructs a new BankAccount with a given initial balance.
     *
     * @param initialBalance the starting balance of the account
     */
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    /**
     * Withdraws a specified amount from the account if sufficient balance is available.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }
}
