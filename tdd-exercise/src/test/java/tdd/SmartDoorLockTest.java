package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int WEAK_PIN = 1234;
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
}
