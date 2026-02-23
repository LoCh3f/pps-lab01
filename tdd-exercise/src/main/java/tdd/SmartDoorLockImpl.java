package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean open;
    private int pin = 0;
    public SmartDoorLockImpl() {
        this.open = true;
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
    public void unlock(int pin) {

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
