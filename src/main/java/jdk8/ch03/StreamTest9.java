package jdk8.ch03;

import java.util.Arrays;
import java.util.List;

public class StreamTest9 {

    /**
     * 流存在短路运算，找到满足条件的，后面就不执行了
     * @param args
     */
    public static void main(String[] args) {
        //找到列表中长度为5的第一个单词，并且输出长度
        List<String> list= Arrays.asList("hello","world","hello world");

        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5).findFirst().ifPresent(System.out::println);



        System.out.println("----------------------------------------------");
        List<String> list1= Arrays.asList("hello","world","hello world");
        list1.stream().mapToInt(item ->{
            int length = item.length();
            //注意此处的输出，这里才是重点哈,此处的输出从第一个开始找，一直输出，直到找到第一个满足条件的，
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


    }
}
