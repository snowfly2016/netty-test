package jdk8.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest8 {


    /**
     * 比较流 串行 并行流
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);

        for(int i = 0; i< 5000000;i++){
            list.add(UUID.randomUUID().toString());
        }

        System.out.println("start sort");

        long starttime = System.nanoTime();

        list.stream().sorted().count();

        long endtime = System.nanoTime();

        long m = TimeUnit.NANOSECONDS.toMillis(endtime - starttime);

        System.out.println("sort time:"+m);

        List<String> list1 = new ArrayList<>(5000000);

        for(int i = 0; i< 5000000;i++){
            list1.add(UUID.randomUUID().toString());
        }

        System.out.println("-------------------------------------------------");
        System.out.println("start sort");

        long starttime1 = System.nanoTime();

        list1.parallelStream().sorted().count();

        long endtime1 = System.nanoTime();

        long m1 = TimeUnit.NANOSECONDS.toMillis(endtime1 - starttime1);

        System.out.println("sort time:"+m1);
    }



}
