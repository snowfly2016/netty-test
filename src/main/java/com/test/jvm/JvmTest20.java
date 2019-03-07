package com.test.jvm;

import java.lang.reflect.Method;

public class JvmTest20 {

    public static void main(String[] args) throws Exception{

        JvmTest16 jvmTest161 = new JvmTest16("loader1");
        JvmTest16 jvmTest162 = new JvmTest16("loader2");

        Class<?> classz1 = jvmTest161.loadClass("com.test.jvm.MyPerson");
        Class<?> classz2 = jvmTest162.loadClass("com.test.jvm.MyPerson");

        //为啥运行结果是true？
        /**
         * jvmTest161去加载MyPerson类的时候，是委托给系统类加载器加载的，系统类加载器可以加载该类
         * jvmTest162去加载MyPerson类的时候，也是根据委托机制委托给系统类加载器，系统类加载器发现已经加载了，直接返回之前的，
         * 因此两个是相等的；
         *
         */
        System.out.println(classz1 ==  classz2);

        Object object1 = classz1.newInstance();
        Object object2 = classz2.newInstance();

        Method method = classz1.getMethod("setMyPerson",Object.class);
        method.invoke(object1,object2);

    }
}
