package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest6 {

    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("hello","world","hello world");

        List<String> list= Arrays.asList("hello","world","hello world");

        //没有终止操作的流，中间操作才会输出或者说生效
        list.stream().map(item->{
            String s = item.substring(0,1).toUpperCase()+item.substring(1);
            System.out.println("test");
            return s;
        });

        List<String> list1= Arrays.asList("hello","world","hello world");
        list1.stream().map(item->{
            String s = item.substring(0,1).toUpperCase()+item.substring(1);
            System.out.println("test");
            return s;
        }).forEach(System.out::println);
    }
}
