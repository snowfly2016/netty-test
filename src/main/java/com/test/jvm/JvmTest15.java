package com.test.jvm;

public class JvmTest15 {

    public static void main(String[] args) {
        String[] strings = new String[3];

        System.out.println(strings.getClass().getClassLoader());

        System.out.println("----------------------");

        JvmTest15[] jvmTest15s = new JvmTest15[4];
        System.out.println(jvmTest15s.getClass().getClassLoader());

        System.out.println("----------------------");
        //原生类型 没有启动类加载器
        int[] ints= new int[2];
        System.out.println(ints.getClass().getClassLoader());


    }
}
