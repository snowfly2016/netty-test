package com.test.jvm;

import java.lang.reflect.Method;

/**
 * 类加载器的双亲委托模型的好处：
 *
 * 1.可以确保Java核心类库的类型安全；所有的Java应用都至少会引用java.lang.Object类，也就是说在运行期，java.lang.Object这个类会被加载到Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的java.lang.Object类，而且这些类之间还是不兼容的，相互不可见（正是命名空间在发挥着作用）。
 * 借住于双亲委托机制，Java核心类库中的类的加载工作都是由启动类加载器来统一完成，从而确保了Java应用所使用的都是同一个版本的Java核心类库，他们之间是相互兼容的；
 *
 * 2.可以确保Java核心类库所提供的类不会被自定义的类所替代
 *
 * 3.不同的类加载器可以为相同名称binary name的类创建额外的命名空间。相同的名称的类可以并存在Java虚拟机中，只需要用不同的类加载器来加载他们即可。不同类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一个又一个相互隔离的Java类空间，这类技术在很多框架中都得到了实际的应用。
 *
 *
 */
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
