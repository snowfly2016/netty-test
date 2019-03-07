package com.test.jvm;

public class JvmTest18_1 {

    /**
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/classes
     *
     * 将需要加载的类 放置到 /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/classes 目录下；类的加载就是根类加载器；
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        JvmTest16 jvmTest16 = new JvmTest16("loader1");
        jvmTest16.setPath("/Users/elm/Desktop/");

        Class<?> classz = jvmTest16.loadClass("com.test.jvm.JvmTest1");

        System.out.println("class:"+classz);
        System.out.println("class loader:"+classz.getClassLoader());


    }
}
