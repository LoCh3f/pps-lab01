package example.model.bankAccount;

import example.model.AccountHolder;
import example.model.BankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int FRANKLIN_BILL = 100;
    private static final int S_GRANT_BILL = 50;
    private static final int JACKSON_BILL = 20;
    private static final int HAMILTON_BILL = 10;


    public static final int FAKE_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), FRANKLIN_BILL);
        assertEquals(FRANKLIN_BILL, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), FRANKLIN_BILL);
        bankAccount.deposit(FAKE_ID, S_GRANT_BILL);
        assertEquals(FRANKLIN_BILL, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), FRANKLIN_BILL);
        bankAccount.withdraw(accountHolder.id(), S_GRANT_BILL + JACKSON_BILL);
        assertEquals(JACKSON_BILL + HAMILTON_BILL, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), FRANKLIN_BILL);
        bankAccount.withdraw(FAKE_ID, S_GRANT_BILL + JACKSON_BILL);
        assertEquals(FRANKLIN_BILL, bankAccount.getBalance());
    }

}
