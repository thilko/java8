package com.thilko.java8;

import org.junit.Test;

import java.util.Optional;

@SuppressWarnings("all")
public class H_Optionals {

    @Test
    public void nullObjects() {
        Customer darth = new Customer();

        // check if present
        boolean isPresent = darth.getEmail().isPresent();

        // no if/else needed
        darth.getEmail().ifPresent((email) -> sendEmailTo(email));

        // default email
        String email = darth.getEmail().orElse("blackhole@deathstar.com");

        // from a supplier
        darth.getEmail().orElseGet(() -> {
            return callTheJedi();
        });
    }

    private String callTheJedi() {
        return "joda@force.com";
    }

    private void sendEmailTo(String email) {
    }


    public static class Customer {
        private String name;
        private Optional<String> email;

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return email;
        }
    }
}
