package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean open;
    private int pin;
    private static final int DEFAULT_PIN = 1111;
    public SmartDoorLockImpl() {
        this.open = true;
        this.pin = DEFAULT_PIN;
    }
    @Override
    public void setPin(final int pin) throws IllegalStateException {
       if (this.open) {
           this.pin = pin;
       } else {
           throw new IllegalStateException("The Door is locked, cannot set up new pin");
       }
    }

    @Override
    public void unlock(final int pin) {
        if (this.pin == pin) {
            this.open = !this.open;
        }
    }

    @Override
    public void lock() {
        this.open = false;
    }

    @Override
    public boolean isLocked() {
        return !this.open;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
