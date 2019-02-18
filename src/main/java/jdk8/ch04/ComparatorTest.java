package jdk8.ch04;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {
        List<String> list =Arrays.asList("nihao","hello","world","welcome");

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list,(t,s)-> t.length() - s.length());

        System.out.println(list);

        Collections.sort(list,(t,s)-> s.length() - t.length());

        System.out.println(list);

        /**
         *
         */
        Collections.sort(list,Comparator.comparingInt(String::length));
        System.out.println(list);


        Collections.sort(list,Comparator.comparingInt(String::length).reversed());
        System.out.println(list);

        /**
         * 此处报错：涉及lambda表达式的参数推断，以下写法会报错；
         *  Collections.sort(list,Comparator.comparingInt(i -> i.length()).reversed())
         * 为啥此处编译器不能推断出类型呢？
         * list.stream().... Stream<T>.... 这种情况下是可以推断出来的；
         *
         * 关键原因是 <? super T> ，他是向上推断，此处的i是Object类型，多个情况下就会出现推断不出，
         *
         */
        Collections.sort(list,Comparator.comparingInt((String i) -> i.length()).reversed());
        System.out.println(list);

        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);

        list.sort(Comparator.comparingInt((String i) -> i.length()).reversed());
        System.out.println(list);


        Collections.sort(list,Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);

        Collections.sort(list,Comparator.comparingInt(String::length).thenComparing((s,t)->s.toLowerCase().compareTo(t.toLowerCase())));
        System.out.println(list);

        Collections.sort(list,Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        System.out.println(list);

        Collections.sort(list,Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        System.out.println(list);

        Collections.sort(list,Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        System.out.println(list);

        System.out.println("--------------thenComparing 前一次比较结果相同才会生效-------------------");
        List<String> list1 =Arrays.asList("nihao","hello","world","welcome");
        Collections.sort(list1,Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())).thenComparing(Comparator.reverseOrder()));
        System.out.println(list1);

    }
}
