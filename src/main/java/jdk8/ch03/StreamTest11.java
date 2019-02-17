package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest11 {

    public static void main(String[] args) {
        /**
         * 对各自人名打招呼
         *
         */
        List<String> list1 = Arrays.asList("hi","hello","你好");
        List<String> list2 = Arrays.asList("张三","李思","王五","丽斯");

        List<String> result = list1.stream().flatMap(item -> list2.stream().map(item2 -> item+" "+item2))
                .collect(Collectors.toList());
        result.forEach(System.out::println);

    }


}
