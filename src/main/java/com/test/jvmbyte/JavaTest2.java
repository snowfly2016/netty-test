package com.test.jvmbyte;

/**
 * 方法的静态分派
 *
 * Grandpa grandpa = new Father();
 *
 * 以上代码；grandpa 的静态类型是Grandpa；而grandpa的实际类型（真正指向的类型）是Father
 *
 * 变量的静态类型是不会发生变化的，而变量的实际类型是可以发生变化的（多态的一种体现），实际类型在运行期可确定
 *
 * 方法重载，是一种静态行为，编译期就可以完全确定
 * 方法重写：是一种动态行为。
 *
 *
 *
 */
public class JavaTest2 {
    public void test(Father father){
        System.out.println("father");
    }

    public void test(Grandpa grandpa){
        System.out.println("grandpa");
    }

    public void test(Son son){
        System.out.println("son");
    }


    public static void main(String[] args) {
        Grandpa grandpa = new Father();

        Grandpa grandpa1 = new Son();

        JavaTest2 javaTest2 = new JavaTest2();

        javaTest2.test(grandpa);
        javaTest2.test(grandpa1);
    }
}

class Grandpa{

}

class Father extends Grandpa{

}

class Son extends Father{

}