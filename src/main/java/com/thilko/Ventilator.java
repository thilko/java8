package com.thilko;

public interface Ventilator {

    int defaultVentilationFrequency = 4;

    default void start(){
        System.out.printf("Start ventilation with frequency " + defaultVentilationFrequency);
    }

    default void stop(){
        System.out.printf("Stop ventilation");
    }
}
