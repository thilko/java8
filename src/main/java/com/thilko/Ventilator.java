package com.thilko;

public interface Ventilator {

    default void start(){
        System.out.printf("Start ventilation");
    }

    default void stop(){
        System.out.printf("Stop ventilation");
    }
}
