package com.thilko.java8;


import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class B_Annotations {

    // in java8 annotations are allowed everywhere where types occur
    // build type checker with this (http://types.cs.washington.edu/checker-framework/)
    // http://www.infoq.com/articles/Type-Annotations-in-Java-8

    // in type declarations
    private List<@NotNull String> noNullsAllowedHere = new ArrayList<>();

    // nested
    private Map<@NotNull String, @NonEmpty List<String>> files;

    // in throw clauses
    void monitorTemperature() throws @Critical TemperatureException {
    }

    // field, local variable declarations
    @Email String onlyValidEmailAllowedHere = "mw@thilko.com";

    // inheritance
    class UnmodifiableList<String> implements @Special Runnable {
        @Override
        public void run() {
        }
    }

    // intersections
    public <T extends @Critical Comparable<String> & @Special Runnable> void foo() {
    }

    ;

    // at method calls
    List<String> aList = Arrays.<@NonEmpty String>asList("hello", "annotations");

    private Object aNumber;
    // in object casts
    String myString = (@ValidString String) aNumber;

    // in instance of checks
    boolean isNonNull = myString instanceof @NotNull String;

    // we can repeat annotations now
    @Repeatable(Editors.class)
    public @interface Editor {
        String name() default "";
    }

    public @interface Editors {
        Editor[] value();
    }

    // apt tool is removed, it is in the compiler now since Java 7
    // javac -processor <class1>[,<class2>,<class3>...]


    @Target({TYPE_USE, TYPE_PARAMETER})
    public @interface Special {
    }

    @Target({TYPE_USE, TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    @Target({TYPE_USE})
    public @interface Critical {
    }

    @Target({TYPE_USE})
    public @interface Email {
    }

    @Target({TYPE_USE})
    public @interface ValidString {
    }

    private class TemperatureException extends Exception {
    }
}
