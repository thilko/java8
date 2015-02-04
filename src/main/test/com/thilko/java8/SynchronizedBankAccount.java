package com.thilko.java8;

import java.util.concurrent.locks.StampedLock;

public class SynchronizedBankAccount implements BankAccount{
    private long balance;

    public SynchronizedBankAccount(long balance) {
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized void withdraw(long amount) {
        balance -= amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}
