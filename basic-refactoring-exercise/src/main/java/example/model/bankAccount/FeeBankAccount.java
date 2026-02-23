package example.model.bankAccount;

import example.model.AccountHolder;
import example.model.BankAccount;

public class FeeBankAccount implements BankAccount {

    private final BankAccount bankAccount;
    private final int fee;

    public FeeBankAccount(final AccountHolder holder, final double balance, final int fee) {
        this.bankAccount = new SimpleBankAccount(holder,balance);
        this.fee = fee;
    }
    @Override
    public double getBalance() {
        return this.bankAccount.getBalance();
    }

    @Override
    public void deposit(final int userID, final double amount) {
        this.bankAccount.deposit(userID, amount);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        this.bankAccount.withdraw(userID, amount);
        this.applyFee(userID, this.fee);
    }

    private void applyFee(final int userID, final int fee){
        this.bankAccount.withdraw(userID, fee);
    }
}