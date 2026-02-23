package tdd.lock.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.lock.SmartDoorLock;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int WEAK_PIN = 1234;
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
        lock.unlock(SmartDoorLock.DEFAULT_PIN);
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
    public void testWrongPin() {
        lock.lock();
        lock.unlock(WEAK_PIN);
        lock.unlock(WEAK_PIN);
        assertEquals(EXPECTED_ATTEMPTS,lock.getFailedAttempts());
    }
    @Test
    public void testBlockState() {
        lock.lock();
        for (int i = 0; i <5;i++) {
            lock.unlock(WEAK_PIN);
        }
        assertTrue(lock.isBlocked());
    }

}
