// Represents a single transaction record, so we can show a history later.
public class Transaction {

    private String type;    // e.g. "Withdraw", "Deposit", "Transfer Sent", "Transfer Received"
    private double amount;
    private String details; // extra info, e.g. which account a transfer went to

    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    // toString() controls how this object prints when we do System.out.println(transaction)
    // or print it inside a list - this is what makes the history readable.
    @Override
    public String toString() {
        return type + " | Amount: " + amount + " | " + details;
    }
}