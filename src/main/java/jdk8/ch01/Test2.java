package jdk8.ch01;

/**
 * 理解清楚函数式接口的定义
 *
 *
 */
@FunctionalInterface
interface Interfaces {

    void test();

    String toString();
}



public class Test2 {
    public void myTest(Interfaces interfaces){
        System.out.println(1);
        interfaces.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        //传统匿名类
        test2.myTest(new Interfaces() {
            @Override
            public void test() {
                System.out.println("mytest");
            }
        });

        System.out.println("-------------------------------");
        //lambde
        test2.myTest(()->{
            System.out.println("mytest");
        });

        System.out.println("-------------------------------");
        Interfaces interfaces =()->{
            System.out.println("mytest");
        };

        /**
         * class jdk8.ch01.Test2$$Lambda$2/932172204
         * class java.lang.Object
         * interface jdk8.ch01.Interfaces
         */
        System.out.println(interfaces.getClass());
        System.out.println(interfaces.getClass().getSuperclass());
        System.out.println(interfaces.getClass().getInterfaces()[0]);
    }

}