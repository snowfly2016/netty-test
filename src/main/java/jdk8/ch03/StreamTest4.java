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

        //计算每个数字的平方
        List<Integer> list1= Arrays.asList(1,2,3,4,5,6,7);
        list1.stream().map(item->item*item).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------------------------");

        //多个集合合并为一个集合
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1),
                Arrays.asList(2,3,4,5),
                Arrays.asList(6,7,8,9));
        //
        stream.flatMap(lists ->lists.stream()).map(integer -> integer*integer).forEach(System.out::println);

    }
}
