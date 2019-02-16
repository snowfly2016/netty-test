package jdk8.ch03;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest3 {

    /**
     * 使用JDK8 要考虑使用流的方式来操作
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("hello","world","hello world");

        /**
         * <A> A[] toArray(IntFunction<A[]> generator);
         *
         */
        //Lambda表达式方式
        String[] stringArray = stringStream.toArray(length ->new String[length]);
        Arrays.asList(stringArray).forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        Stream<String> stringStream1 = Stream.of("hello","world","hello world");

        //方法引用方式
        String[] stringArray1 = stringStream1.toArray(String[]::new);
        Arrays.asList(stringArray1).forEach(System.out::println);

        Stream<String> stringStream2 = Stream.of("hello","world","hello world");
        //<R> R collect(Supplier<R> supplier,
        //                  BiConsumer<R, ? super T> accumulator,
        //                  BiConsumer<R, R> combiner);
        List<String> list = stringStream2.collect(Collectors.toList());
        list.forEach(System.out::println);

        Stream<String> stringStream3 = Stream.of("hello","world","hello world");


        System.out.println("-------------------------3------------------------------");

        //注意这个写法的理解需要理解doc文档的定义
        List<String> list1 = stringStream3.collect(()-> new ArrayList<>(),
                (theList,item)->theList.add(item),
                (t1,t2)-> t1.addAll(t2));
        list1.forEach(System.out::println);

        System.out.println("--------------------------4-----------------------------");

        Stream<String> stringStream4 = Stream.of("hello","world","hello world");

        //注意这个写法的理解需要理解doc文档的定义
        List<String> list2 = stringStream4.collect(LinkedList::new,
                LinkedList::add,
                LinkedList::addAll);
        list2.forEach(System.out::println);

        System.out.println("--------------------------5-----------------------------");

        Stream<String> stringStream5 = Stream.of("hello","world","hello world");
        String concat = stringStream5.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        System.out.println(concat);



        System.out.println("--------------------------6-----------------------------");
        Stream<String> stringStream6 = Stream.of("hello","world","hello world");

        List<String> list3 = stringStream6.collect(Collectors.toCollection(ArrayList::new));
        list3.forEach(System.out::println);


        System.out.println("--------------------------7-----------------------------");
        Stream<String> stringStream7 = Stream.of("hello","world","hello world");

        Set<String> stringSet = stringStream7.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(stringSet.getClass());
        stringSet.forEach(System.out::println);

        System.out.println("--------------------------8-----------------------------");
        Stream<String> stringStream8 = Stream.of("hello","world","hello world");
        String string = stringStream8.collect(Collectors.joining());
        System.out.println(string);





    }




}
