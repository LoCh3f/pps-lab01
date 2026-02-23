package example.model;

import example.model.bankAccount.SimpleBankAccount;

public class FeeBankAccount extends SimpleBankAccount {

    public FeeBankAccount(final AccountHolder holder, double balance) {
        super(holder, balance);
    }

    private void applyFee() {
     super.withdraw(this.);
    }
}