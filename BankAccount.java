/**
 * Represents a simple bank account that supports basic operations.
 */
public class BankAccount {
    private double balance;

    /**
     * Constructs a new BankAccount with a zero balance.
     */
    public BankAccount() {
        balance = 0.0;
    }

    /**
     * Returns the current balance of the account.
     * 
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the account.
     * 
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    /**
     * Withdraws the specified amount from the account.
     * 
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
