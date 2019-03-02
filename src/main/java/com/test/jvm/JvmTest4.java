package com.test.jvm;

import java.util.Random;

/**
 * 当一个接口在初始化时，并不要求其父接口都完成初始化(System.out.println(MyChild5.b); 输出6 且删除MyParent5.class)
 * 只有在真正使用到父接口的时候（如引用接口中所定义的常量时），才会初始化
 *
 */
public class JvmTest4 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);

        System.out.println(MyChild5.c);

        System.out.println(MyChild5.d);


    }
}

interface MyParent5{
    public static int a=5;
    public static int x= new Random().nextInt(2);
}

interface MyChild5 extends MyParent5{
    public static int b=6;
    public static int c= new Random().nextInt(2);
    public static final int d= new Random().nextInt(2);
}
