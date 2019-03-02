package com.test.jvm;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量类的初始化
 * 注意：这里指的是将常量存放到了JvmTest1的常量池中，之后JvmTest1与MyParent2就没有任何关系了，甚至，我们可以将MyParent2的class文件删除
 *
 * 助记符：
 * ldc表示将int，float或是String类型的常量值从常量池中推送至栈顶
 * bipush表示将单字节（-128~127）的常量值推送至栈顶
 * sipush表示将一个短整型常量值（-32768~32767）推送至栈顶
 * iconst_1表示将int类型1推送至栈顶（iconstm1,iconst_0,iconst_1~iconst_5）-1~5
 *
 *
 *
 * 反编译当前类，结果如下：
 * elmdembp:classes xx$ javap -c com.test.jvm.JvmTest1
 * Compiled from "JvmTest1.java"
 * public class com.test.jvm.JvmTest1 {
 *   public com.test.jvm.JvmTest1();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #4                  // String hello world
 *        5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       11: bipush        7
 *       13: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
 *       16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       19: sipush        128
 *       22: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
 *       25: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       28: iconst_1
 *       29: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
 *       32: return
 * }
 */

public class JvmTest1 {

    public static void main(String[] args) {

        //System.out.println(MyParent2.str);
        //System.out.println(MyParent2.aShort);
        System.out.println(MyParent2.i);
        //System.out.println(MyParent2.m);
    }
}


class MyParent2{
    //public static String str ="hello world";

    //public static final String str ="hello world";

    //public static final short aShort =7;

    public static final int i =128;

    /**
     *
     */
    //public static final int m =1;

    static {
        System.out.println("MyParent2 static block");
    }
}