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
}
