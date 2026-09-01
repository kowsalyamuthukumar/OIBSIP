import java.util.HashMap;
import java.util.Map;

// Represents the whole bank: holds every account, and lets you look one up by ID.
// Using a HashMap means looking up an account by ID is fast, instead of scanning a list.
public class Bank {

    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // Returns the Account object for a given ID, or null if it doesn't exist
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }
}