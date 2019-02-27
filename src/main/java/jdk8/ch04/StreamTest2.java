package jdk8.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","a","b","c");
        //class java.util.Arrays$ArrayList
        System.out.println(list.getClass());
        //没有中间操作直接调用head中的foreach方法，
        list.stream().forEach(System.out::println);
        System.out.println("-----------------------------");
       // list.stream().parallel().forEach(System.out::println);
        //有中间操作，调用ReferencePipeline的foreach方法，
       // list.stream().map(i->i).forEach(System.out::println);


        //
        List<String> list1 = Arrays.asList("hello","world","welcome","a","b","c");
        Stream<String> stream = list1.stream();

        System.out.println("-------------------------------");

        Stream<String> stream1 = stream.map(i->i);


    }
}
