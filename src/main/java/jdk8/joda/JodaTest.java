package jdk8.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class JodaTest {

    public static void main(String[] args) {

        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        // 两年前第3个月最后一天
        DateTime dateTime = new DateTime();
        DateTime dateTime1 =dateTime.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();

        System.out.println(dateTime1.toString("yyyy-MM-dd"));



    }
}
