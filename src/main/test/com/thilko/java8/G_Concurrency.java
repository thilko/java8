package com.thilko.java8;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.LongStream;

@SuppressWarnings("all")
public class G_Concurrency {

    @Test
    public void forkJoinPool() {
        // ForkJoinPool is an extension of the AbstractExecutorService
        // work stealing algorithm
        // break work in smaller parts recursively
        ForkJoinPool forkJoinPool = new ForkJoinPool();
    }

    @Test
    public void completedFuture() {
    }

    @Test
    public void concurrentHashMap() {
        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        data.put("harry", 30);
        data.put("horst", 32);
        data.put("willi", 35);

        data.forEach((name, age) -> System.out.println(name + age));
        data.computeIfPresent("horst", (name, age) -> 33);
    }


    @Test
    public void adder() {
        DoubleAdder doubleAdder = new DoubleAdder();
        doubleAdder.add(2.1);

        LongAdder longAdder = new LongAdder();
        longAdder.decrement();
        longAdder.increment();
    }

    @Test
    public void stampedLock() throws InterruptedException {
        long start = System.currentTimeMillis();
        runBankExample(new NotThreadSafeBankAccount(0));
        long end = System.currentTimeMillis();
        System.out.println(String.format("Duration not thread safe: " + (end - start)));

        start = System.currentTimeMillis();
        runBankExample(new SynchronizedBankAccount(0));
        end = System.currentTimeMillis();
        System.out.println(String.format("Duration synchronized: " + (end - start)));

        start = System.currentTimeMillis();
        runBankExample(new StampedLockBankAccount(0));
        end = System.currentTimeMillis();

        System.out.println(String.format("Duration stamped lock: " + (end - start)));
    }

    private void runBankExample(BankAccount ba) throws InterruptedException {
        Runnable work1 = () -> {
            LongStream.range(0, 100000000).boxed().forEach((amount) -> {
                ba.deposit(amount);

            });
        };

        Runnable work2 = () -> {
            LongStream.range(0, 100000000).boxed().forEach((amount) -> {
                ba.withdraw(amount);

            });
        };

        Thread thread = new Thread(work1);
        thread.start();

        Thread thread1 = new Thread(work2);
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println(ba.getBalance());
    }
}
