package bankService;
import bankService.exceptions.*;

/**
 * Abstracts the general concepts of a bank account.
 * @author Tariq King
 */
public abstract class Account {

    protected long accountNumber;
    protected double balance;
    protected Customer owner;

    public Account(Customer owner, double balance, long accountNumber) {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Customer getOwner() { return this.owner; }

    public long getAccountNumber() { return this.accountNumber; }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        else {
            this.balance = this.balance+amount;
        }
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

}