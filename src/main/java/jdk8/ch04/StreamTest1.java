package jdk8.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest1 {

    /**
     * 异常的压制
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","a","b","c");

        NullPointerException nullPointerException = new  NullPointerException("first exception");

        try(Stream<String> stream = list.stream()){
            stream.onClose(()->{
                System.out.println("xxxxxxxx");
                //throw new NullPointerException("first exception");
                //throw nullPointerException;
                throw new NullPointerException("first exception");
            }).onClose(()->{
                System.out.println("yyyyyyyyy");
                //throw new ArithmeticException("second exception");
                //throw nullPointerException;
                throw new NullPointerException("second exception");
            }).forEach(System.out::println);
        }

    }
}
