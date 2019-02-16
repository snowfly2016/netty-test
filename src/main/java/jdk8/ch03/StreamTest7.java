package jdk8.ch03;

import java.util.stream.IntStream;

public class StreamTest7 {

    public static void main(String[] args) {
        //这个会无限执行 干爆内存哈哈
        IntStream.iterate(0,i->(i+1)%2).distinct().limit(6).forEach(System.out::println);

        //需要注意使用的顺序，
        IntStream.iterate(0,i->(i+1)%2).limit(6).distinct().forEach(System.out::println);
    }

}
