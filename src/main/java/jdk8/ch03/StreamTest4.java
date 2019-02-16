package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {


    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("hello","world","hello world");

        //将list中的元素转化为大写
        List<String> list= Arrays.asList("hello","world","hello world");

        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        List<Integer> list1= Arrays.asList(1,2,3,4,5,6,7);
        list1.stream().map(item->item*item).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------------------------");



    }
}
