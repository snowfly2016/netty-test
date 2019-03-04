package com.test.jvm;

public class JvmTest6 {
    public static void main(String[] args) {
        //验证初始化一个接口时，并不会先初始化他的父接口
        //System.out.println(MyChildInterface.thread);
        //验证初始化一个接口时，并不会先初始化他的父接口
        //System.out.println(MyInterface.thread);

        //System.out.println(MyChildInterface1.thread);
        //System.out.println(MyInterface1.thread);
        //验证{}语句块的执行时间
        //new CTest();
        //new CTest();

        //System.out.println(MyInterface2.thread);
        //System.out.println(MyChildInterface2.thread);
        System.out.println(MyChildInterface3.thread);

    }
}

class MyInterface2{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyInterface2 invoked");
        }
    };
}

class MyChildInterface2 extends MyInterface2{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyChildInterface2 invoked");
        }
    };
}

class MyChildInterface3 extends MyChildInterface2{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyChildInterface3 invoked");
        }
    };
}



interface MyInterface1{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyInterface1 invoked");
        }
    };
}

class MyChildInterface1 implements MyInterface1{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyChildInterface1 invoked");
        }
    };
}



interface MyInterface{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyInterface invoked");
        }
    };
}

interface MyChildInterface extends MyInterface{
    public static  Thread thread = new Thread(){
        {
            System.out.println("MyChildInterface invoked");
        }
    };
}

class CTest {

    {
        System.out.println("CTest invoked");
    }

    static {
        System.out.println("CTest static invoked");
    }

    public CTest() {
        System.out.println("CTest construct invoked");
    }
}