package bankService;

import bankService.exceptions.InsufficientFundsException;
import bankService.exceptions.InvalidAmountException;

/**
 * Specialized bank account on which interest can be earned.
 * @author Tariq King
 */
public class SavingsAccount extends Account {

    private double annualInterestRate;
    private final double defaultAnnualInterestRate = 0.0005;
    private double unpaidInterest;

    public SavingsAccount(Customer owner, double startBalance, long accountNumber) {
        super(owner, startBalance, accountNumber);
        this.annualInterestRate = defaultAnnualInterestRate;
        this.unpaidInterest = 0.0;
    }

     @Override
     public void withdraw(double amount) throws InsufficientFundsException {
       if (amount > this.balance) {
            throw new InsufficientFundsException();
        }
        else {
            this.balance = this.balance - amount;
        }
     }

    public void calculateUnpaidInterest()
    {
        unpaidInterest = unpaidInterest + this.balance * annualInterestRate;
    }

    public double getUnpaidInterest() { return unpaidInterest; }

    public void payInterest()
    {
        try {
            deposit(unpaidInterest);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate)
    {
        this.annualInterestRate = annualInterestRate;
    }
}