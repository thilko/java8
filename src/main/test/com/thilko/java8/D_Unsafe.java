package com.thilko.java8;

import org.junit.Test;
import sun.misc.*;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class D_Unsafe {

    // http://java.dzone.com/articles/understanding-sunmiscunsafe
    // http://bytescrolls.blogspot.de/2011/04/interesting-uses-of-sunmiscunsafe.html

    @Test
    public void codeItLikeACGangster() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        long l = unsafe.allocateMemory(100);
        unsafe.freeMemory(l);
    }
}
