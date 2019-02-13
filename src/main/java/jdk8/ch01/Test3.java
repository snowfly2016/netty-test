package jdk8.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test3 {

    /**
     * 在将函数作为一等公民的语言中，Lambda表达式的类型是函数。
     * 但在Java中，Lambda表达式是对象，他们必须依附于一类特别的对象类型--函数式接口（functional interface）
     * @param args
     */
    public static void main(String[] args) {

        //通过上下文判断表达式的类型：lambda的类型；类型推断
        TestInterfaceOne testInterfaceOne = ()->{};
        System.out.println(testInterfaceOne.getClass().getInterfaces()[0]);

        //通过上下文判断表达式的类型
        TestInterfaceTwo testInterfaceTwo = ()->{};
        System.out.println(testInterfaceTwo.getClass().getInterfaces()[0]);

        //****************************start****************************
        /**
         * 以下两种方式等价
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        }).start();

        new Thread(()->{
            System.out.println("hello world");
        }).start();
        //******************************end**************************

        /**
         * lambda实现list集合中首字母大写需求
         */
        List<String> list = Arrays.asList("hello","world","hello world");

        list.forEach(item -> System.out.println(item.toUpperCase()));

        /**
         * 将list放置到新的list中
         */
        //diamond 钻石语法或者菱形语法
        List<String> list1 = new ArrayList<>();

        list.forEach(item -> list1.add(item.toUpperCase()));
        list1.forEach(item -> System.out.println(item.toUpperCase()));

        /**
         * parallelStream()
         * stream()
         *
         * 中间流 结点流 pipeline
         *
         * Function<? super T, ? extends R> mapper
         */
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));

        /**
         * 与list.stream().map(item->item.toUpperCase()).forEach(item-> System.out.println(item)); 等价
         *
         * 方法引用 给定一个输入，返回一个输出
         * R apply(T t)
         * toUpperCase() 参数为空，输入是调用改方法的对象作为输入，因此是符合function的标准的
         *
         */
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        /**
         * item -> item.toUpperCase()
         * 与
         * String::toUpperCase
         * 如果是类类型::实例方法，它多对应的lambda表达式的第一个参数就是调用这个方法的参数，
         *
         * Function<T, R>
         * JDK8接口中可以定义静态方法
         *
         */
        Function<String,String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);

    }
}

@FunctionalInterface
interface TestInterfaceOne{

    void myMethodOne();
}

@FunctionalInterface
interface TestInterfaceTwo{

    void myMethodTwo();
}
