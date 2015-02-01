package com.thilko.java8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.DoubleUnaryOperator;

public class G_Concurrency {

    @Test
    public void forkJoinPool(){
        // ForkJoinPool is an extension of the AbstractExecutorService
        // work stealing algorithm
        // break work in smaller parts recursively
        ForkJoinPool forkJoinPool = new ForkJoinPool();
    }

    @Test
    public void completedFuture(){ }

    @Test
    public void concurrentHashMap(){
        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        data.put("harry", 30);
        data.put("horst", 32);
        data.put("willi", 35);

        data.forEach((name, age) -> System.out.println(name + age));
        data.computeIfPresent("horst", (name, age) -> 33);
    }


    @Test
    public void adder(){
        DoubleAdder doubleAdder = new DoubleAdder();
        doubleAdder.add(2.1);

        LongAdder longAdder = new LongAdder();
        longAdder.decrement();
        longAdder.increment();
    }
}
