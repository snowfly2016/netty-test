package com.test.jvm;

public class JvmTest17 {


    public static void main(String[] args) throws Exception{
        JvmTest16 jvmTest16 = new JvmTest16("loader1");

        Class<?> classz = jvmTest16.loadClass("com.test.jvm.MySample");
        System.out.println("class: "+classz.hashCode());

        //如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用，因此不会实例化MyCat对象，
        // 即没有对MyCat进行主动使用，这里就不会加载MyCat Class（注意：这里未必不会加载噢）
        Object object = classz.newInstance();
    }




}
