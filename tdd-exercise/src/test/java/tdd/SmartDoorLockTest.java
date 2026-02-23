package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int WEAK_PIN = 1234;

    @Test
    public void testSetUpLock() {
        SmartDoorLock lock = new SmartDoorLockImpl();
        assertFalse(lock.isLocked());
    }
    @Test
    public void testSetPin() {
        SmartDoorLock lock = new SmartDoorLockImpl();
        lock.setPin(WEAK_PIN);
        lock.lock();
        lock.unlock(WEAK_PIN);
        assertFalse(lock.isLocked());
    }

}
