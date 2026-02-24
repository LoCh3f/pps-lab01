package tdd.lock.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.lock.SmartDoorLock;

import static org.junit.jupiter.api.Assertions.*;
import static tdd.lock.LockSpecification.DEFAULT_PIN;

public class SmartDoorLockTest {
    private static final int WEAK_PIN = 1234;
    private static final int NOT_COMPLIANT_PIN = 123;
    private static final int EXPECTED_ATTEMPTS = 2;
    private SmartDoorLock lock;
    @BeforeEach
    public void BeforeEach() {
         lock = new SmartDoorLockImpl();

    }
    @Test
    public void testSetUpLock() {
        assertFalse(lock.isLocked());
    }
    @Test
    public void testLock() {
        lock.lock();
        assertTrue(lock.isLocked());
    }
    @Test
    public void testLockUnlock() {
        lock.lock();
        lock.unlock(DEFAULT_PIN);
        assertFalse(lock.isLocked());
    }
    @Test
    public void testNewPin() {
        lock.setPin(WEAK_PIN);
        lock.lock();
        lock.unlock(WEAK_PIN);
        assertFalse(lock.isLocked());
    }
    @Test
    public void testChangePinWhenLocked() {
        lock.lock();
        assertThrows(IllegalStateException.class,() -> lock.setPin(WEAK_PIN));
    }
    @Test
    public void testNotCompliantPin() {
        assertThrows(IllegalArgumentException.class, () -> lock.setPin(NOT_COMPLIANT_PIN));
    }
    @Test
    public void testWrongPin() {
        lock.lock();
        lock.unlock(WEAK_PIN);
        lock.unlock(WEAK_PIN);
        assertEquals(EXPECTED_ATTEMPTS,lock.getFailedAttempts());
    }
    @Test
    public void testRemainingAttempts() {
        lock.lock();
        lock.unlock(WEAK_PIN);
        lock.unlock(WEAK_PIN);
        lock.unlock(WEAK_PIN);
        assertEquals(EXPECTED_ATTEMPTS,lock.getRemainingAttempts());

    }
    @Test
    public void testBlockState() {
        lock.lock();
        this.blockLock(lock);
        assertTrue(lock.isBlocked());
    }
    @Test
    public void testResetFromState() {
        lock.setPin(WEAK_PIN);
        lock.lock();
        this.blockLock(lock);
        lock.reset(DEFAULT_PIN,WEAK_PIN);
        assertFalse(lock.isBlocked());
    }
    private void blockLock(final SmartDoorLock lock){
        for (int i = 0; i <5;i++) {
            lock.unlock(NOT_COMPLIANT_PIN);
        }
    }
}
