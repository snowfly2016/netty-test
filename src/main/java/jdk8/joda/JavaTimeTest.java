package jdk8.joda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public class JavaTimeTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate);

        System.out.println(localDate.getYear()+" "+localDate.getMonth());

        LocalDate localDate1 = LocalDate.of(2011,3,12);

        MonthDay monthDay = MonthDay.of(3,25);
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2011,3,25));
        if(monthDay.equals(monthDay1)){
            System.out.println("=======");
        }else {
            System.out.println("===+++++==");
        }
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDate localDate2 = localDate.plus(2,ChronoUnit.WEEKS);
        System.out.println(localDate2);
        LocalDate localDate3 = localDate.minus(2,ChronoUnit.MONTHS);
        System.out.println(localDate3);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());

        Set<String> set = ZoneId.getAvailableZoneIds();
        set.stream().forEach(System.out::println);

        Set<String> strings = new TreeSet<String>();
        strings.addAll(set);

        YearMonth yearMonth = YearMonth.of(2019,2);
        System.out.println(yearMonth.isLeapYear());
        System.out.println(yearMonth.lengthOfYear());
        System.out.println(yearMonth.lengthOfMonth());

        LocalDate localDate4 = LocalDate.of(2019,2,28);
        LocalDate localDate5 = LocalDate.of(2018,2,28);
        Period period = Period.between(localDate4,localDate5);
        System.out.println(period.isZero());
    }
}
