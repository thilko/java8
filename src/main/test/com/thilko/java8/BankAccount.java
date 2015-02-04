package com.thilko.java8;

public interface BankAccount {

    public void deposit(long amount);

    public void withdraw(long amount);

    public long getBalance();
}
