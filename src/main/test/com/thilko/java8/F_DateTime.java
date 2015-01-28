package com.thilko.java8;

import org.junit.Test;

import java.time.*;

@SuppressWarnings("all")
public class F_DateTime {

    // have a look at joda time :-)

    @Test
    public void instant() {
        Instant now = Instant.now();

        System.out.println(now);
    }

    @Test
    public void clock() {
        Clock theClock = Clock.system(ZoneId.systemDefault());

        Instant currentTime = theClock.instant();
        System.out.println(currentTime);
    }

    @Test
    public void date() {
        MonthDay dateOfBirth = MonthDay.of(7, 7);

        LocalDate bornAt = dateOfBirth.atYear(1979);
        System.out.println(bornAt);
    }

    @Test
    public void duration() {
        Duration aLongMeeting = Duration.ofHours(3);

        Instant endOfMeeting = Instant.now().plus(aLongMeeting);
    }

}
