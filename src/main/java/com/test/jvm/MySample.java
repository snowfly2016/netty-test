package com.test.jvm;

public class MySample {

    public MySample(){
        System.out.println("MySample is loaded by: "+ this.getClass().getClassLoader());

        new MyCat();

        // after add
        System.out.println("from MySample: "+ MyCat.class);
    }
}
