package com.test.jvm;

import java.util.UUID;

/**
 * 为啥MyParent3静态代码块的内容会输出呢？和JvmTest2的不一样呢？
 * 编译器能不能确定值
 * 当一个常量的值并非编译期间可以确定的，那么其值就不会放到调用类的常量池中，这时在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 *
 *
 */
public class JvmTest2 {
    public static void main(String[] args) {

        System.out.println(MyParent3.str);
    }
}

class MyParent3{
    /**
     * 编译期不能确定值，只有在运行期才能确定值，因此会输出静态代码块
     */
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static code");
    }
}
