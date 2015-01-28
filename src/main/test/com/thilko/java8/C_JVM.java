package com.thilko.java8;

public class C_JVM {

    // no permanent generation anymore. It is replaced through the Metaspace.
    // The permanent generation contains information about classes, such as bytecode, names and JIT information.
    // no java.lang.OutOfMemoryError: PermGen anymore...
    // interned Strings are removed from PermGen since Java 7

    // instead native memory is used now, capacity is limited by the amount of available memory
    // metaspace has garbage collection too
    // it can overflow too
    // you can limit it with -MaxMetaspaceSize

    // ... but it has dynamic limits which can be configured
    // application runs longer
}
