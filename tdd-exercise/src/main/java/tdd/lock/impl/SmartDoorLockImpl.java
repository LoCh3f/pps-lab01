package tdd.lock.impl;

import tdd.lock.SmartDoorLock;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean open;
    private boolean blocked;
    private int pin;
    private int attempts;

    public SmartDoorLockImpl() {
        this.open = true;
        this.blocked = false;
        this.pin = DEFAULT_PIN;
        this.attempts = 0;

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
        if (this.pin == pin && this.isLocked()) {
            this.open = !this.open;
        } else if (this.pin != pin) {
            this.newWrongAttempt();
        }

    }
    private void newWrongAttempt() {
        this.attempts += 1;
        if (this.attempts == MAX_ATTEMPTS) {
            this.block();
        }

    }

    @Override
    public void lock() {
        if (!this.isLocked()) {
           this.changeState();
        }
    }

    private void changeState() {
        this.open = !this.open;
    }

    @Override
    public boolean isLocked() {
        return !this.open;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    private void block() {
        this.blocked = true;
    }

    @Override
    public int getRemainingAttempts() {
        return MAX_ATTEMPTS - this.attempts;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {

    }
}
