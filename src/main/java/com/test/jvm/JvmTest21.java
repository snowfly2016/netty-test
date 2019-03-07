package com.test.jvm;

import java.lang.reflect.Method;

public class JvmTest21 {

    public static void main(String[] args) throws Exception{

        JvmTest16 jvmTest161 = new JvmTest16("loader1");
        JvmTest16 jvmTest162 = new JvmTest16("loader2");

        jvmTest161.setPath("/Users/elm/Desktop/");
        jvmTest162.setPath("/Users/elm/Desktop/");


        Class<?> classz1 = jvmTest161.loadClass("com.test.jvm.MyPerson");
        Class<?> classz2 = jvmTest162.loadClass("com.test.jvm.MyPerson");

        //为啥运行结果是flase？
        /**
         * 同一个命名空间内的类是相互可见的;
         * 子类加载器的命名空间包含所有父类加载器的命名空间；因此由子类加载器加载的类能看见父类加载器加载的类。例如系统类加载器加载的类能看见根类加载器加载的类
         * 由父类加载器加载的类不能看见子加载器加载的类
         * 如果两个加载器之间没有直接或间接的父子关系，那么他们各自加载的类相互不可见
         */
        System.out.println(classz1 ==  classz2);

        Object object1 = classz1.newInstance();
        Object object2 = classz2.newInstance();

        Method method = classz1.getMethod("setMyPerson",Object.class);
        method.invoke(object1,object2);

    }
}
