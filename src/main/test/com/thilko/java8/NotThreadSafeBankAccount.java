package com.thilko.java8;

import java.util.concurrent.locks.StampedLock;

public class NotThreadSafeBankAccount implements BankAccount {
    private long balance;

    public NotThreadSafeBankAccount(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    public long getBalance() {
        return balance;
    }
}
