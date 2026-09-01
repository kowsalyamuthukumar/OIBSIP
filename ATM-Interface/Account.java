// Represents one bank account: holds the account holder's details and balance.
public class Account {

    private String accountId;
    private String pin;
    private double balance;

    public Account(String accountId, String pin, double balance) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    // Checks if the entered PIN matches this account's PIN
    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    // Adds money to the balance (used by deposit and by transfer-receiving)
    public void deposit(double amount) {
        balance += amount;
    }

    // Removes money from the balance. Returns true if it succeeded (enough funds),
    // false if there wasn't enough balance to cover it.
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // insufficient funds
        }
        balance -= amount;
        return true;
    }
}