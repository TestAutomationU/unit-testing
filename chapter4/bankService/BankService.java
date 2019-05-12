package bankService;

import notificationService.Notification;
import java.util.ArrayList;

/**
 * Implements functionality for performing bank transactions and retrieving information on the bank. Interacts with a notification service for sending messages to customers.
 * @author Tariq King
 */
public class BankService implements Banking {

    private String name;
    private int routingNumber;
    private static long checkingStartNumber;
    private static long savingsStartNumber;
    private ArrayList<Account> accounts;
    private Notification notificationService;

    /**
     * Creates and initializes a new bank service.
     * @param name name of the bank.
     * @param routingNumber number used to direct data or documents to the bank.
     * @param checkingStartNumber number used to start the sequence of checking accounts that can be created.
     * @param savingsStartNumber number used to start the sequence of savings accounts that can be created.
     */
    public BankService(String name, int routingNumber, long checkingStartNumber, long savingsStartNumber) {
        this.name = name;
        this.routingNumber = routingNumber;
        accounts = new ArrayList<Account>();
        this.notificationService = notificationService;
        BankService.checkingStartNumber = checkingStartNumber;
        BankService.savingsStartNumber = savingsStartNumber;
        this.notificationService = null;
    }

    /**
     * @return name of the bank.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return number used to direct data or documents to the bank (e.g., checks).
     */
    public int getRoutingNumber() { return this.routingNumber; }

    /**
     * @return a list of bank accounts stored in the bank.
     */
    public ArrayList<Account> getAccounts() { return accounts; }

    /**
     * Create an account for a new or existing customer.
     * @param owner customer that will own the account being created.
     * @param startBalance initial balance of the account.
     * @param accountType type of the account e.g., checking, savings.
     * @return true if the account creation is successful, false otherwise.
     */
    @Override
    public boolean createAccount(Customer owner, double startBalance, AccountType accountType) {
        if(accountType == AccountType.Checking) {
            accounts.add(new CheckingAccount(owner, startBalance, checkingStartNumber));
            checkingStartNumber++;
            return true;
        }
        else if (accountType == AccountType.Savings) {
            accounts.add(new SavingsAccount(owner, startBalance, savingsStartNumber));
            savingsStartNumber++;
            return true;
        }
        return false;
    }

    /**
     * Connect to a specified notification service.
     * @param notificationService the notification service being connected to (e.g., e-mail, text message).
     */
    public void connectToNotificationService(Notification notificationService) {
        this.notificationService = notificationService;
        notificationService.connect();
    }

    /**
     * Disconnects from the currently connected notification service.
     */
    public void disconnectFromNotificationService() {
        notificationService.disconnect();
        this.notificationService = null;
    }
}