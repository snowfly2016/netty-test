package com.test.jvm;

/**
 * Parent2 static block
 * 3
 * --------------------
 * Parent2 doSomething
 *
 */
public class JvmTest11 {
    public static void main(String[] args) {
        System.out.println(Child2.a);
        System.out.println("--------------------");
        Child2.doSomething();

    }
}
class Parent2{
    static int a = 3;

    static {
        System.out.println("Parent2 static block");
    }

    static void doSomething(){
        System.out.println("Parent2 doSomething");
    }
}

class Child2 extends Parent2{

    static {
        System.out.println("child2 static block");
    }
}