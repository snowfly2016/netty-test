package jdk8.ch02;

public interface MyInterface1 {

    default void myMethod(){
        System.out.println("MyInterface1");
    }
}
