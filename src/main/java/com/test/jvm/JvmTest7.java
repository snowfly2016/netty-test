package com.test.jvm;

public class JvmTest7 {
    public static void main(String[] args) throws Exception{
        //null
        Class<?> classz = Class.forName("java.lang.String");
        System.out.println(classz.getClassLoader());

        //sun.misc.Launcher$AppClassLoader@18b4aac2
        Class<?> classc = Class.forName("com.test.jvm.C");
        System.out.println(classc.getClassLoader());


    }
}


class C{

}