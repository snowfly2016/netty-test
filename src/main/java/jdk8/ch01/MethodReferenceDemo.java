package jdk8.ch01;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceDemo {

    public static void main(String[] args) {
        List<String> list =Arrays.asList("hello","world"," hi");

        list.forEach(item -> System.out.println(item));

        list.forEach(System.out::println);

    }

}
