package com.test.jvm;

import java.util.Random;

public class JvmTest8 {

    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}

class FinalTest{
    public static final int x=3;
    //public static int x=3;

    //public static int x= new Random().nextInt(3);

    static {
        System.out.println("FinalTest static block");
    }
}
