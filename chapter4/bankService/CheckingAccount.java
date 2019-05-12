package bankService;

import bankService.exceptions.InsufficientFundsException;
import bankService.exceptions.InvalidAmountException;
import notificationService.Notification;

/**
 * Specialized bank account on which checks can be processed.
 * @author Tariq King
 */
public class CheckingAccount extends Account {

    private double minBalance;
    private double overdraftLimit;
    private double overdraftFee;
    private double serviceFee;
    private boolean isOverDrawn;
    private boolean droppedBelowMinBalance;
    private boolean notificationsEnabled;
    private Notification notificationService;

    public CheckingAccount(Customer owner, double startBalance, long accountNumber) {
        super(owner, startBalance, accountNumber);
        this.minBalance=1500.0;
        this.overdraftLimit=0.0;
        this.overdraftFee=30.0;
        this.serviceFee=12.0;
        this.isOverDrawn=false;
        this.droppedBelowMinBalance =false;
        this.notificationsEnabled=false;
        this.notificationService=null;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
      if(amount <= this.balance+overdraftLimit) {
          this.balance = this.balance - amount;
          updateAccountStatus();
          if (isOverDrawn) {
              this.balance = this.balance - overdraftFee;
          }
      }
      else throw new InsufficientFundsException();
    }

    private void updateAccountStatus()
    {
        this.isOverDrawn = this.balance < 0;
        if (this.balance < minBalance) { droppedBelowMinBalance = true; }
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        super.deposit(amount);
        updateAccountStatus();
    }

    public void deductFees() {
        updateAccountStatus();
        if (this.droppedBelowMinBalance) {
            this.balance = this.balance - this.serviceFee;
            this.droppedBelowMinBalance = false;
        }
    }
    
    public void processCheck(Check checkToProcess)
    {
        try {
            withdraw(checkToProcess.getAmount());
            if (notificationsEnabled) {
                notificationService.notify("Processed Check #" + checkToProcess.getCheckNumber()
                                           + " in the amount of $" + checkToProcess.getAmount() + ".", owner.getEmail());
            }
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    public void enableNotifications(Notification notificationService) {
        notificationsEnabled=true;
        this.notificationService = notificationService;
    }

    public void disableNotifications() {
        notificationsEnabled=false;
        this.notificationService=null;
    }
}