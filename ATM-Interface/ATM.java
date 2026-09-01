import java.util.*;

// Represents the ATM machine itself: handles login and the menu of operations
// for whichever account is currently logged in.
public class ATM {

    private Bank bank;
    private Scanner scanner;

    // Holds every transaction made during THIS session (resets each time someone logs in)
    private List<Transaction> sessionHistory;

    public ATM(Bank bank, Scanner scanner) {
        this.bank = bank;
        this.scanner = scanner;
        this.sessionHistory = new ArrayList<>();
    }

    // Handles the login attempt. Returns the Account if login succeeds, or null if it fails
    // after 3 wrong tries (task requirement: deny access after 3 incorrect attempts).
    public Account login() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String id = scanner.next();
            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            Account account = bank.getAccount(id);

            // account != null check comes first, so checkPin() is never called on a null account
            if (account != null && account.checkPin(pin)) {
                System.out.println("Login successful. Welcome, " + id + "!");
                return account;
            }

            attempts++;
            System.out.println("Incorrect User ID or PIN. Attempts remaining: " + (3 - attempts));
        }

        System.out.println("Too many failed attempts. Access denied.");
        return null;
    }

    // Runs the main menu loop for a logged-in account. Keeps showing options
    // until the user chooses Quit.
    public void showMenu(Account account) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            // Using switch-case to route to the right operation based on the menu number
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showHistory();
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    deposit(account);
                    break;
                case 4:
                    transfer(account);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye, " + account.getAccountId() + "!");
                    running = false; // this ends the while loop
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    private void showHistory() {
        System.out.println("\n--- Transaction History ---");
        if (sessionHistory.isEmpty()) {
            System.out.println("No transactions yet this session.");
        } else {
            // Print every transaction recorded so far - relies on Transaction's toString()
            for (Transaction t : sessionHistory) {
                System.out.println(t);
            }
        }
    }

    private void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        // withdraw() returns false if balance was insufficient - we check that here
        // rather than the Account object printing anything itself.
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            sessionHistory.add(new Transaction("Withdraw", amount, "Balance: " + account.getBalance()));
        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void deposit(Account account) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
        sessionHistory.add(new Transaction("Deposit", amount, "Balance: " + account.getBalance()));
    }

    private void transfer(Account account) {
        System.out.print("Enter recipient Account ID: ");
        String recipientId = scanner.next();
        Account recipient = bank.getAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer successful. New balance: " + account.getBalance());
            sessionHistory.add(new Transaction("Transfer Sent", amount, "To: " + recipientId));
        } else {
            System.out.println("Insufficient Funds.");
        }
    }
}