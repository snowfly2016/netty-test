package jdk8.ch03;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest5 {

    public static void main(String[] args) {
        Stream<String> stringStream = Stream.generate(UUID.randomUUID()::toString);
        //findFirst()返回optional对象；为啥？避免NPE问题
        stringStream.findFirst().ifPresent(System.out::println);

        System.out.println("------------------------------------------");

        //限制，防止无限输出 limit()
        Stream.iterate(1,item -> item + 2).limit(6).forEach(System.out::println);

        System.out.println("------------------------------------------");

        /**
         * 找出该流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后在取流中的前两个元素，最后求出流中元素的总和
         * sum()返回int
         */
        Stream<Integer> stream = Stream.iterate(1,item -> item + 2).limit(6);

        //Map() 和 mapToInt()都能实现，Map()存在装箱，性能有消耗，选择最优的
        int sum = stream.filter(integer -> integer >2).mapToInt(integer -> integer * 2).skip(2).limit(2).sum();

        System.out.println(sum);

        System.out.println("------------------------------------------");
        /**
         * 找出该流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后在取流中的前两个元素，最后求出流中最小的元素的
         * min()返回OptionalInt
         */
        Stream<Integer> stream1 = Stream.iterate(1,item -> item + 2).limit(6);
        stream1.filter(integer -> integer >2).mapToInt(integer -> integer * 2).skip(2).limit(2).min().ifPresent(System.out::println);
        System.out.println("------------------------------------------");

        /**
         * 找出该流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后在取流中的前两个元素，最后求出流中最小的元素、总和
         *
         */
        Stream<Integer> stream2 = Stream.iterate(1,item -> item + 2).limit(6);
        IntSummaryStatistics summaryStatistics = stream2.filter(integer -> integer >2).mapToInt(integer -> integer * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getSum());
        System.out.println("------------------------------------------");

        /**
         * 问题：
         * stream has already been operated upon or closed
         *
         */
        Stream<Integer> stream3 = Stream.iterate(1,item -> item + 2).limit(6);

        System.out.println(stream3);
        System.out.println(stream3.filter(i -> i > 2));
        System.out.println(stream3.distinct());

        System.out.println("------------------------------------------");

        /**
         * 解决问题：
         * stream has already been operated upon or closed
         *
         */
        Stream<Integer> stream4 = Stream.iterate(1,item -> item + 2).limit(6);

        System.out.println(stream4);
        Stream<Integer> stream5 =  stream4.filter(i -> i > 2);
        System.out.println(stream5);
        Stream<Integer> stream6 =  stream5.filter(i -> i > 2);
        System.out.println(stream6);
    }


}
