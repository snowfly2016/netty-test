package com.test.jvm;

public class JvmTest5 {

    public static void main(String[] args) {
        Singleton singleton =  Singleton.getSingleton();

        System.out.println("c1 "+Singleton.counter1);
        System.out.println("c2 "+Singleton.counter2);
    }
}

class Singleton{
    public static int counter1;
    //public static int counter1 = 1;
    //public static int counter2 = 0;

    private static Singleton singleton = new Singleton();

    private Singleton(){
        counter1++;
        counter2++;
        System.out.println(counter1);
        System.out.println(counter2);
        System.out.println("-------------------------");
    }

    /**
     * 调整 counter2的位置，观察输出的结果
     */
    public static int counter2 = 0;

    public static Singleton getSingleton(){
        return singleton;
    }
}