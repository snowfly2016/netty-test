package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest10 {

    public static void main(String[] args) {
        //找出集合中所有单词，并且去重
        List<String> list= Arrays.asList("hello welcome","world hello","hello world hello","hello welcome");

        /*list.stream().map(item -> item.split(" ")).distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);*/

        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


}
