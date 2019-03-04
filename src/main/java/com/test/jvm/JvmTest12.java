package com.test.jvm;

/**
 * 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
 *
 */
public class JvmTest12 {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class<?> classz = classLoader.loadClass("com.test.jvm.CL");

        System.out.println(classz);

        System.out.println("-----------------------");

        classz = Class.forName("com.test.jvm.CL");

        System.out.println(classz);
    }
}

class CL{
    static {
        System.out.println("Class cl");
    }
}