package jdk8.ch01;

@FunctionalInterface
public interface MyInterface {

    void test();

    //报错
    //void test2();

    //错误消失？为啥
    @Override
    String toString();
}
