package com.test.jvm;

public class JvmTest10 {

    static {
        System.out.println("JvmTest10 static block");
    }
    public static void main(String[] args) {
        Parent1 parent1;
        System.out.println("-------------------");
        parent1 = new Parent1();
        System.out.println("-------------------");
        System.out.println(parent1.a);
        System.out.println("-------------------");
        System.out.println(Child1.b);

    }
}

class Parent1{
    static int a = 3;

    static {
        System.out.println("Parent1 static block");
    }
}

class Child1 extends Parent1{
    static int b =4;
    static {
        System.out.println("child1 static block");
    }
}
