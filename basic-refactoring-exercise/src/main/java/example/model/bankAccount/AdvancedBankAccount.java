package example.model.bankAccount;

import example.model.AccountHolder;
import example.model.BankAccount;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AdvancedBankAccount implements BankAccount {

    public static final int YOU_ARE_BROKE_BRO = 0;
    private final double activeInterest;
    private final BankAccount account;

    private AdvancedBankAccount(final BankAccount account, final double activeInterest) {
        this.account = account;
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

    public void gainInterest(final int userID) {
        this.account.deposit(userID,this.calculateInterest());
    }

    private double calculateInterest() {
        return this.account.getBalance() * this.activeInterest;
    }

    public static class Builder{
        private double activeInterest;
        private Optional<String> name;
        private Optional<String> surname;
        private Optional<Integer> id;
        private Optional<Integer> fee;
        private double balance;

        public Builder setUpBuild() {
            this.name = Optional.empty();
            this.surname = Optional.empty();
            this.id = Optional.empty();
            this.fee = Optional.empty();
            this.activeInterest = 0.0;
            this.balance = 0.0;
            return this;
        }

        public Builder setName(final String name) {
            this.name = Optional.of(name);
            return this;
        }
        public Builder setSurname(final String surname) {
            this.surname = Optional.of(surname);
            return this;
        }
        public Builder setId(final int id) {
            this.id = Optional.of(id);
            return this;
        }
        public Builder setBalance(final double balance) {
            this.balance = balance;
            return this;
        }
        public Builder setFee(final int fee) {
            this.fee = Optional.of(fee);
            return this;
        }
        public Builder setActiveInterest(final double activeInterest) {
            this.activeInterest = activeInterest;
            return this;
        }

        public AdvancedBankAccount Build() {
            if(this.name.isEmpty() || this.surname.isEmpty() || this.id.isEmpty()) {
                throw new IllegalStateException("Missing account Holder Data");
            }
            final BankAccount account;
            final AccountHolder holder = new AccountHolder(this.name.get(), this.surname.get(), this.id.get());
            return this.fee.map(integer
                            -> new AdvancedBankAccount(new FeeBankAccount(holder, this.balance, integer), this.activeInterest))
                    .orElseGet(()
                            -> new AdvancedBankAccount(new SimpleBankAccount(holder, this.balance), this.activeInterest));
        }


    }
}
