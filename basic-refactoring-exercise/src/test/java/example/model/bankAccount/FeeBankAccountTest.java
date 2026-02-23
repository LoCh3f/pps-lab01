package example.model.bankAccount;

import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeeBankAccountTest {


    private static final int FEE = 1;
    public static final int WASHINGTON_BILL = 1;
    public static final int JEFFERSON_BILL = 2;
    public static final int LINCOLN_BILL = 5;
    public static final int HAMILTON_BILL = 10;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new FeeBankAccount(accountHolder, 0,FEE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), WASHINGTON_BILL);
        assertEquals(WASHINGTON_BILL, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), JEFFERSON_BILL);
        bankAccount.deposit(SimpleBankAccountTest.FAKE_ID, WASHINGTON_BILL);
        assertEquals(JEFFERSON_BILL, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), LINCOLN_BILL);
        bankAccount.withdraw(accountHolder.id(), WASHINGTON_BILL);
        assertEquals(WASHINGTON_BILL + JEFFERSON_BILL, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), HAMILTON_BILL);
        bankAccount.withdraw(SimpleBankAccountTest.FAKE_ID, HAMILTON_BILL);
        assertEquals(HAMILTON_BILL, bankAccount.getBalance());
    }

    @Test
    void isFeeCorrectlyApplied() {
        bankAccount.deposit(accountHolder.id(),HAMILTON_BILL);
        bankAccount.withdraw(accountHolder.id(), LINCOLN_BILL);
        assertEquals(JEFFERSON_BILL + JEFFERSON_BILL,bankAccount.getBalance());
    }
}