package com.thilko.java8;

import com.thilko.A500;
import com.thilko.Person;
import com.thilko.PersonFactory;
import com.thilko.Ventilator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambdas {

    // java is not a purely functional language, has no special type for functions
    // is not side effect free

    @Test
    public void lambdaForAnonymousClasses() {
        List<String> names = Arrays.asList("Luke", "Darth Vader", "Joda");

        // anonymous class
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // function lambda
        names.sort((o1, o2) -> {
            return o1.compareTo(o2);
        });

        // statement lambda
        names.sort((o1, o2) -> o1.compareTo(o2));

        // method reference to an object method
        names.sort(String::compareTo);
    }

    @Test
    public void effectiveFinal() {
        Long localVar = 42L;

        Function<Integer, Long> lambda = (first) -> first * localVar;
        lambda.apply(3);
    }

    @Test
    public void lambdaHasAFunctionalInterfaceAType() {
        // Interface with exactly one abstract method
        // SAM Types
        // can be marked with @FunctionalInterface
        Function<Long, Long> aFunction;
        Predicate<Long> aPredicate;
        Consumer<String> aConsumer;
        Runnable aThread;
    }


    @Test
    public void constructorReferences() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }

    @Test
    public void defaultMethods() {
        Ventilator a500 = new A500();
        a500.stop();
    }

    @Test
    public void streamApi() {
        List<Integer> numberList = Arrays.asList(4, 6, 2, 3, 100, 29, 10, 9, 22983, 223);

        numberList.stream()
                .filter((number) -> number % 2 == 0) // intermediate operation
                .filter((number) -> number > 100)
                .map(String::valueOf)
                .collect(Collectors.toList());  // terminal operation

        boolean numberOver100 = numberList.stream().anyMatch(number -> number > 100);
    }

    @Test
    public void lambdaAsArgments() {
        Predicate<Integer> argumentCheck = number -> number > 100 && number < 50;
        test(argumentCheck);
    }

    @Test
    public void overloading(){
        A500 machine = new A500();
        machine.configure(System.out::println);
    }

    @Test
    public void intStream(){
        List<Integer> result = IntStream.range(10, 20).boxed().collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    @Test
    public void staticInterfaceMethods(){
        Stream.empty();
    }

    public void test(Predicate<Integer> checker) {

    }
}
