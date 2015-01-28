package com.thilko.java8;


import java.util.ArrayList;
import java.util.List;

public class Annotations {

    // aim: to build type checker

    // repeatable annotation

    // apt tool is removed

    // javax.lang.reflect vs. javax.lang.model

    private List< @NotNull String> list = new ArrayList<>();  // illegal in Java 7; permitted in Java 8

    Map< @NotNull String,  @NonEmpty List< @Readonly Document>> files;

    void monitorTemperature() throws  @Critical TemperatureException { ... }
}
