package jdk8.ch02;

public interface MyInterface {
    default void myMethod(){
        System.out.println("MyInterface");
    }
}
