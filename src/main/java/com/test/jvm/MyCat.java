package com.test.jvm;

public class MyCat {

    public MyCat(){
        //
        System.out.println("MyCat is loaded by: "+this.getClass().getClassLoader());

        // after add
        //System.out.println("from MyCat: "+ MySample.class);
    }


}
