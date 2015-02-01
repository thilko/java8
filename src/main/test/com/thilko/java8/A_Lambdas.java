package com.thilko.java8;

import com.sun.source.doctree.DocTree;
import com.thilko.A500;
import com.thilko.Person;
import com.thilko.PersonFactory;
import com.thilko.Ventilator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class A_Lambdas {

    @Test
    public void basicLambdaExample() {
        // a lambda without args and one expression
        BooleanSupplier a = () -> true;

        // lambda with one argument
        IntConsumer b = (int aNumber) -> System.out.println(aNumber);

        // type can be omitted
        IntConsumer c = (aNumber) -> System.out.println(aNumber);

        // parantheses can be omitted if we have one arg without type
        IntConsumer d = aNumber -> System.out.println(aNumber);

        // two arguments without type
        BiConsumer<String, Integer> e = (first, second) -> System.out.println(first.charAt(second));

        // two arguments with type
        BiConsumer<String, Integer> f = (String first, Integer second) -> System.out.println(first.charAt(second));

        // multiple statements can be wrapped with brackets
        BiConsumer<String, Integer> g = (String first, Integer second) -> {
            for (int i = 0; i < second; i++) {
                System.out.println(first);
            }
        };

        // return statements must be wrapped with brackets
        Function<String, Integer> h = (argument) -> {
            return argument.length();
        };

        // java 8 does not introduce a special type for functions. It use SAM(Single Abstract Method) types for it.
        // Interface with exact one abstract method
        // can be marked with @FunctionalInterface
    }

    @FunctionalInterface
    public interface MyFunctionInterface {

        // compiler error if not exactly one abstract method is given
        public void calculate();
    }

    @Test
    public void existingSamTypes() {
        // jump to type explorer here
        Function<Long, Long> aFunction;

        // an operation that takes an integer and returns void
        IntConsumer intConsumer = (aNumber) -> System.out.println(aNumber);

        // an operation that takes a long and returns void
        LongConsumer longConsumer = (aNumber) -> System.out.println(aNumber);

        // Represents a boolean valued operation.
        Predicate<String> predicate = (argToCheck) -> argToCheck != null;

        // Represents a function: an argument and a return value
        Function<String, Integer> lengthCalculator = (theString) -> {
            return theString.length();
        };

        // The compiler does the type matching in a deductive way.
        // How the closure is represented is not specified:
        // http://cr.openjdk.java.net/~briangoetz/lambda/lambda-translation.html
    }

    @Test
    public void defaultMethods() {
        Ventilator a500 = new A500();
        a500.stop();
    }

    @Test
    public void overloading() {
        A500 machine = new A500();
        machine.configure(System.out::println);
    }

    @Test
    public void staticInterfaceMethods() {
        Stream.empty();
    }

    @Test
    public void lambdasForAnonymousClasses() {
        List<String> names = Arrays.asList("Luke", "Darth Vader", "Yoda", "Jabba");

        // Old school, usage of an anonymous class
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // better...
        names.sort((o1, o2) -> {
            return o1.compareTo(o2);
        });

        // better...
        names.sort((o1, o2) -> o1.compareTo(o2));
    }

    @Test
    public void theContextOfTheLambda() {
        // local var does not need to be marked as final but must not be modified
        // otherwise a compiler error will occur
        Long localVar = 42L;

        Function<Integer, Long> lambda = (first) -> first * localVar;
        Long result = lambda.apply(3);

        System.out.println(result);
    }

    @Test
    public void streamApi() {
        List<Integer> numberList = Arrays.asList(4, 6, 2, 3, 100, 29, 10, 9, 22983, 223);

        // we can use forEach now
        numberList.forEach((theNumber) -> System.out.println(theNumber));

        // Collection interface has default method stream() now
        numberList.stream()
                .filter((number) -> number % 2 == 0) // intermediate operation
                .filter((number) -> number > 100) // intermediate operation
                .map(String::valueOf) // intermediate operation
                .collect(Collectors.toList());  // terminal operation

        // ... make it faster with parallel stream
        numberList.parallelStream()
                .filter((number) -> number % 2 == 0) // intermediate operation
                .filter((number) -> number > 100) // intermediate operation
                .map(String::valueOf) // intermediate operation
                .collect(Collectors.toList());  // terminal operation

        boolean numberOver100 = numberList.stream().anyMatch(number -> number > 100);

        // create numbered lists with IntStream
        List<Integer> result = IntStream.range(10, 20).boxed().collect(Collectors.toList());
        result.forEach(System.out::println);

        // ... there are builder too...
        Stream.empty();
        Stream.builder().add("hello").add("world").build().count();
    }

    @Test
    public void executeAroundPatter() {
        // execute around pattern
        perform((timeInMillis) -> {
                    if (timeInMillis % 1000 == 0) {
                        throw new IllegalStateException();
                    }
                }
        );

        perform((timeInMillis) -> {
                    System.out.println("hello world");
                }
        );
    }

    public void perform(LongConsumer operation) {
        // do check before logic here...

        operation.accept(System.currentTimeMillis());

        // do checks afterwards ...
    }

    @Test
    public void accessingContext() {
        IntConsumer intConsumer = aMethod();
        intConsumer.accept(39);
    }

    private IntConsumer aMethod() {
        int theNumber = 42;
        IntConsumer aLambda = (aNumber) -> {
            System.out.println(aNumber * theNumber);
        };

        return aLambda;
    }

    // "this" in a lambda context points to the enclosing class, not the lambda itself

    @Test
    public void hiding() {
        int theNumber = 2;
        IntConsumer aLambda = (aNumber) -> {
            // int theNumber =3; does not compile
            System.out.println(aNumber * theNumber);
        };
    }

    @Test
    public void functionalProgramming() {
        // from ...
        // find all female customers with a bmi over 20 and older than 30 years
        List<Customer> allCustomers = findAllCustomers();
        List<Customer> customerForAdvertisements = new ArrayList<>();

        for (Customer customer : allCustomers) {
            if (customer.bmi() > 30 && customer.isFemale() && customer.olderThan(30)) {
                customerForAdvertisements.add(customer);
            }
        }

        // to...
        customerForAdvertisements = allCustomers.stream().filter(customer -> customer.bmi() > 30)
                .filter(Customer::isFemale)
                .filter(customer -> customer.olderThan(30))
                .collect(Collectors.toList());

        // mapping...
        List<Double> allBmis = allCustomers.stream().map((customer) -> customer.bmi()).collect(Collectors.toList());

    }

    @Test
    public void constructorReferences() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }


    public class Customer {
        private int age;
        private int weightInKg;
        private int heightInMeter;
        private BigDecimal averageIncome;

        public double bmi() {
            return weightInKg / (heightInMeter * 2);
        }

        public boolean isFemale() {
            return false;
        }

        public boolean olderThan(int age) {
            return false;
        }

        public int getAge() {
            return age;
        }

        public int getWeightInKg() {
            return weightInKg;
        }

        public int getHeightInMeter() {
            return heightInMeter;
        }

        public BigDecimal getAverageIncome() {
            return averageIncome;
        }

    }

    private List<Customer> findAllCustomers() {
        return null;
    }

}
