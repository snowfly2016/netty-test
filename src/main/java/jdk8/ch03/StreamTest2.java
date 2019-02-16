package jdk8.ch03;

import java.util.Arrays;
import java.util.List;

public class StreamTest2 {

    /**
     * 流由三部分组成：1.源，2.零个或多个中间操作，3.终止操作
     * 流操作分类：1.惰性求值，2.及早求值;（stream.xxx().yyy().zzz().cont()）
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        System.out.println(list.stream().map(integer -> 2 * integer).reduce(0,Integer::sum));


    }
}
