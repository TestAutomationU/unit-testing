package bankService;

import notificationService.EmailService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CheckingAccountTest {

    Customer customer;
    CheckingAccount checking;

    @BeforeClass
    public void oneTimeSetup() {
        customer = new Customer ("Mickey Mouse", "Disneyland", "Mickey@disneyland.com");
    }

    @BeforeMethod
    public void eachTimeSetup() {
        checking = new CheckingAccount(customer, 250.00, 987654321);
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Check mockCheck;

    /**
     * Customers should be able to process a valid check on a checking account with sufficient funds
     * Scenario:
     * 1. Given a customer's checking account with an initial balance of $250.00
     * 2. When I process a check for $100.00
     * 3. Then the new account balance should be $150.00
     */
    @Test
    public void processingCheckWithSufficientFunds_DecreasesBalanceByAmount() {
        // Given
        when(mockCheck.getAmount()).thenReturn(100.00);
        when(mockCheck.getCheckNumber()).thenReturn(4321);

        // When
        checking.processCheck(mockCheck);

        // Then
        assertEquals(checking.getBalance(), 150.00);
    }

    @Spy
    EmailService spiedEmailService = new EmailService();

    /**
     * Customers should be able to receive notifications that a check has been processed.
     * Scenario:
     * 1. Given I enable E-mail notifications on the checking account
     * 2. And I have a valid check #4321 for amount $100.00
     * 3. When I process check #4321
     * 4. Then the transaction notification should be sent via e-mail
     * 5. And the account balance should be $150.00
     */
    @Test
    public void processingCheckWithNotificationsEnabled_DecreaseBalanceAndSendsMessage() {
        // Given
        checking.enableNotifications(spiedEmailService);
        when(mockCheck.getCheckNumber()).thenReturn(4321);
        when(mockCheck.getAmount()).thenReturn(100.0);

        // When
        checking.processCheck(mockCheck);

        // Then
        verify(spiedEmailService).notify(contains("Check #4321"), eq(checking.getOwner().getEmail()));
        assertEquals(checking.getBalance(), 150.00);
    }
}