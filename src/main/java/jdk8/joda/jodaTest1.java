package jdk8.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class jodaTest1 {

    //标准utc时间 2018-11-12-04T09:22:53.8762
    public static Date convertUTCtoDate(String utcDate){
        DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        return dateTime.toDate();
    }
    public static String convertDateToUTC(Date date){
        DateTime dateTime = new DateTime(date,DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDateTo2UTC(Date date,String dateFormat){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(dateFormat);
    }


    public static void main(String[] args) {
        System.out.println(convertUTCtoDate("2018-11-12T09:22:53.876z"));

        System.out.println(convertDateToUTC(new Date()));

        System.out.println(convertDateTo2UTC(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }
}
