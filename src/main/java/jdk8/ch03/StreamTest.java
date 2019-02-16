package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    /**
     * 流的3中创建方式
     * @param args
     */
    public static void main(String[] args) {
        Stream stream = Stream.of("hello","world","hello world");

        String[] array = new String[]{"hello","world","hello world"};
        Stream stream1 = Stream.of(array);
        Stream stream2 = Arrays.stream(array);

        List<String> list = Arrays.asList(array);
        Stream stream3 = list.stream();


    }



}
