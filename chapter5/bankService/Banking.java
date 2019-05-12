package bankService;

import notificationService.Notification;
import java.util.ArrayList;

/**
 * Interface specifying banking capabilities such as creating and accessing customer accounts.
 * @author Tariq King
 */
public interface Banking {
    /**
     * @return name of the bank.
     */
    public String getName();

    /**
     * @return number used to direct data or documents to the bank (e.g., checks).
     */
    public int getRoutingNumber();

    /**
     * Create an account for a new or existing customer.
     * @param owner customer that will own the account being created.
     * @param startBalance initial balance of the account.
     * @param accountType type of the account e.g., checking, savings.
     * @return true if the account creation is successful, false otherwise.
     */
    public boolean createAccount(Customer owner, double startBalance, AccountType accountType);

    /**
     * @return a list of bank accounts stored in the bank
     */
    public ArrayList<Account> getAccounts();

    /**
     * Connect to a specified notification service.
     * @param notificationService the notification service being connected to (e.g., e-mail, text message).
     */
    public void connectToNotificationService(Notification notificationService);

    /**
     * Disconnects from the currently connected notification service.
     */
    public void disconnectFromNotificationService();

    /**
     * Enumerated type for referring to a specific type of account (e.g., checking or savings).
     */
    public enum AccountType {Checking, Savings};
}