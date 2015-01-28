package com.thilko;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class A500 implements Ventilator {

    @Override
    public void start() {
        System.out.println("Start A500 ventilation.");
    }

    public void configure(LongConsumer longValue){
        longValue.accept(42L);
    }

    //public void configure(IntConsumer intValue){
    //    intValue.accept(420);
    //}
}
