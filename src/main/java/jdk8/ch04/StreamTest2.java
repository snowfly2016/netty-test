package jdk8.ch04;

import java.util.Arrays;
import java.util.List;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","a","b","c");

        list.stream().forEach(System.out::println);
    }
}
