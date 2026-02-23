package example.model.bankAccount;

import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.bankAccount.interest.Interest;

import java.util.Optional;

public class AdvancedBankAccount implements BankAccount {

    public static final int YOU_ARE_BROKE_BRO = 0;

    private final int fee;
    private final double activeInterest;
    private final BankAccount account;

    private AdvancedBankAccount(final BankAccount account,final double balance, final int fee, final double activeInterest) {
        this.account = account;
        this.fee = fee;
        this.activeInterest = activeInterest;
    }

    @Override
    public double getBalance() {
        return this.account.getBalance();
    }

    @Override
    public void deposit(int userID, double amount) {
        if (amount <= YOU_ARE_BROKE_BRO) {
            throw new IllegalStateException("You cannot deposit nothing or a negative amount of money");
        }
        this.account.deposit(userID,amount);
    }

    @Override
    public void withdraw(int userID, double amount) {
        if (amount <= YOU_ARE_BROKE_BRO) {
            throw new IllegalStateException("You cannot withdraw nothing or a negative amount of money");
        }
        this.account.withdraw(userID, amount);
    }

    public static class Builder{
        private int fee = 0 ;
        private double activeInterest = 0;
        private Optional<BankAccount> account = Optional.empty();
        private double balance = 0;

        public AdvancedBankAccount Build() {
            if (this.account.isEmpty()) {
                throw new IllegalStateException("Account is not set, cannot build a Bank Account");
            }
            final var newAccount = account.get();
            this.account = Optional.empty();
            return new AdvancedBankAccount(newAccount,this.balance,this.fee,this.activeInterest);
        }

        public Builder setFee(final int fee) {
            this.fee = fee;
            return this;
        }

        public Builder setActiveInterest(final double activeInterest) {
            this.activeInterest = activeInterest;
            return this;
        }

        public Builder setHolder(final BankAccount account) {
            this.account = Optional.of(account);
            return this;
        }

        public Builder setBalance(final double balance) {
            this.balance = balance;
            return this;
        }
    }
}
