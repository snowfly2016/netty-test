package com.test.jvm;

/**
 * JvmTest9 static block
 * Parent static block
 * child static block
 * 4
 *
 *
 */
public class JvmTest9 {
    static {
        System.out.println("JvmTest9 static block");
    }
    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}

class Parent{
    static int a = 3;

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent{
    static int b =4;
    static {
        System.out.println("child static block");
    }
}
