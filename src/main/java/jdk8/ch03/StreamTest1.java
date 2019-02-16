package jdk8.ch03;

import java.util.stream.IntStream;

public class StreamTest1 {

    public static void main(String[] args) {

        IntStream.of(new int[]{5,6,7,8,9}).forEach(System.out::println);

        System.out.println("--------------------------");

        IntStream.range(3,9).forEach(System.out::println);

        System.out.println("--------------------------");

        IntStream.rangeClosed(3,9).forEach(System.out::println);

        System.out.println("--------------------------");

    }
}
