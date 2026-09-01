import java.util.*;

// Entry point of the program. Sets up sample accounts, then starts the login + menu flow.
public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the bank and add a couple of sample accounts so you can test
        // login and transfer without building a registration screen (not required by the task).
        Bank bank = new Bank();
        bank.addAccount(new Account("user1", "1234", 5000.0));
        bank.addAccount(new Account("user2", "5678", 2000.0));

        System.out.println("===== Welcome to the Java ATM =====");
        System.out.println("(Test accounts: user1/1234 or user2/5678)");

        ATM atm = new ATM(bank, scanner);

        Account loggedInAccount = atm.login();

        // Only show the menu if login actually succeeded
        if (loggedInAccount != null) {
            atm.showMenu(loggedInAccount);
        }

        scanner.close();
    }
}