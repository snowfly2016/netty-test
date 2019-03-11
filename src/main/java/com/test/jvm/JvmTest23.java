package com.test.jvm;

import sun.misc.Launcher;

/**
 * 在运行期，一个Java类是由该类的完全限定名（binary name 二进制名）和用于加载该类的定义类加载器（defining loader）所共同决定的；
 * 如果同样名字（即相同的完全限定名）的类是由两个不同的加载器所加载，那么这些类就是不同的，即便.class文件的字节码完全一样，
 * 并且从相同的位置加载亦如此
 *
 * 在Oracle的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则运行出错，提示如下错误信息：
 * java -Dsun.boot.class.path=./ com.test.jvm.JvmTest23
 * Error occurred during initialization of VM
 * java/lang/NoClassDefFoundError: java/lang/Object
 *
 * *************************************************************
 * 类加载器本身也是类，类加载器是谁来加载的呢？
 *
 * 数组是jvm运行时创建的；
 *
 * 内建于jvm中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类，当jvm启动时，一块特殊的机器码会运行，
 * 它会加载扩展类加载器与系统类加载器，这块特殊的机器码叫做启动类加载器Bootstrap
 *
 * 启动类加载器并不是Java类，而其他的加载器则都是Java类
 * 启动类加载器是特定于平台的机器指令，它负责开启整个加载过程
 *
 * 所有类加载器（除了启动类加载器）都被实现为Java类，不过，总归要有一个组件来加载第一个Java类加载器，从而让整个加载过程能够顺利进行下去，加载第一个纯Java类加载器就是启动类加载器的职责
 * 启动类加载器还会负责加载供jre正常运行所需要的基本组件，这包括java.util与java.lang包中的类等等。
 *
 *
 */
public class JvmTest23 {

    /**
     * java com.test.jvm.JvmTest23
     * idea run
     * 两个运行结果的区别；
     * java com.test.jvm.JvmTest23 运行结果中系统类加载是一个.
     * idea run运行结果是一串（idea在运行期修改了path，追加了一些路径）
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("-----------------------------");
        System.out.println(System.getProperty("java.class.path"));


        // 输入 null
        System.out.println(ClassLoader.class.getClassLoader());
        System.out.println("-----------------------------");
        //扩展类加载器与系统类加载器也是由启动类加载器所加载的
        System.out.println(Launcher.class.getClassLoader());
        ClassLoader.getSystemClassLoader();
        System.out.println("---------------new--------------");
        /**
         * 默认情况下，未定义指向appclassloader
         *
         * 显式指定系统类加载器
         * 将JvmTest16作为系统类加载器
         *
         * JvmTest16 中必须定义一个只有ClassLoader参数的构造方法
         *
         * java -Djava.system.class.loader=com.test.jvm.JvmTest16 com.test.jvm.JvmTest23
         *
         */
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(JvmTest23.class.getClassLoader());

        System.out.println(JvmTest16.class.getClassLoader());

        System.out.println(ClassLoader.getSystemClassLoader());
    }

    /**
     * ClassLoader.getSystemClassLoader()方法的doc注解文档
     *
     *
     * context class loader 上下文类加载器
     * sets it as the context class loader of the invoking Thread。
     *
     * sun.misc.Launcher l = sun.misc.Launcher.getLauncher();
     *
     * openjdk
     * http://openjdk.java.net/
     *
     * http://grepcode.com
     * SystemClassLoaderAction 中run方法会自动调用
     * Constructor<?> ctor = Class.forName(cls, true, parent)
     *             .getDeclaredConstructor(new Class<?>[] { ClassLoader.class });
     *
     * Thread.currentThread().setContextClassLoader(sys);
     *
     * Launcher源码阅读
     *
     * Class.forName(...)
     *
     */
}
