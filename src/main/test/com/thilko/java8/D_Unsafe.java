package com.thilko.java8;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sun.misc.*;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class D_Unsafe {

    // http://java.dzone.com/articles/understanding-sunmiscunsafe
    // http://bytescrolls.blogspot.de/2011/04/interesting-uses-of-sunmiscunsafe.html
    // https://code.google.com/p/objenesis/

    @Test
    public void codeItLikeACGangster() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        long l = unsafe.allocateMemory(100);
        unsafe.freeMemory(l);
    }

    @Test
    public void testDirectIntArray() throws Exception {
        long maximum = Integer.MAX_VALUE + 1L;
        DirectIntArray directIntArray = new DirectIntArray(maximum);
        directIntArray.setValue(0L, 10);
        directIntArray.setValue(maximum, 20);
        assertEquals(10, directIntArray.getValue(0L));
        assertEquals(20, directIntArray.getValue(maximum));

        directIntArray.destroy();
    }

    class DirectIntArray {

        private final static long INT_SIZE_IN_BYTES = 4;

        private final long startIndex;
        private final Unsafe unsafe;

        public DirectIntArray(long size) throws NoSuchFieldException, IllegalAccessException {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);

            startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
            unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte) 0);
        }

        public void setValue(long index, int value) {
            unsafe.putInt(index(index), value);
        }

        public int getValue(long index) {
            return unsafe.getInt(index(index));
        }

        private long index(long offset) {
            return startIndex + offset * INT_SIZE_IN_BYTES;
        }

        public void destroy() {
            unsafe.freeMemory(startIndex);
        }

    }


}
