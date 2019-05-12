package bankApplication;

import bankService.*;
import notificationService.*;
import static bankService.Banking.AccountType.*;

/**
 * A commercial bank or credit union with a limited scope of services.
 * @author Tariq King
 */
public class Bank
{
    private Banking bankService;
    private Notification notificationService;

    /**
     * Creates a new bank when provided with the required banking and notification services.
     * @param bankService service for holding customer information and performing bank transactions
     * @param notificationService service for notifying customers of account activity
     */
    public Bank(Banking bankService, Notification notificationService)
    {
        this.bankService = bankService;
        this.bankService.connectToNotificationService(notificationService);
    }

    /**
     * Access the Banking Service.
     * @return A reference to the banking service for this bank
     */
    public Banking getBankService() {
        return this.bankService;
    }
    /**
     * Access the Notification Service.
     * @return A reference to the notification service for this bank
     */
    public Notification getNotificationService() { return this.notificationService; }

    /**
     * Driver for the Bank Application.
     */
    public static void main(String [] args) {

        // Initialize required services
        Banking bankService = new BankService("Partners Federal Credit Union", 256074974, 500135210, 100769310);
        Notification notificationService = new EmailService();

        // Create a new bank and the first customer
        Bank bank = new Bank(bankService, notificationService);
        Customer customer = new Customer("Mickey Mouse", "Disneyland", "Mickey@Disneyland.com");

        // Create a checking and savings accounts for the customer
        bank.getBankService().createAccount(customer, 2000.0, Checking);
        bank.getBankService().createAccount(customer, 10000.0, Savings);
        bank.getBankService().createAccount(customer, 10000.0, Savings);

        // Print some information on the bank
        System.out.println("Bank Name: " + bank.getBankService().getName());
        System.out.println("Routing Number: " + bank.getBankService().getRoutingNumber());
        System.out.println("Total Accounts: " + bank.getBankService().getAccounts().size());
    }

}