package com.test.source;

import io.netty.util.concurrent.FastThreadLocal;

public class FastThreadLoaclTest {
    private static FastThreadLocal<Object> fastThreadLocal = new FastThreadLocal<>();


    public static void main(String[] args) {
        new Thread(()->{
            fastThreadLocal.set(new Object());
            Object object = fastThreadLocal.get();

            System.out.println(object);

            while (true){
                fastThreadLocal.set(new Object());
                try{
                    Thread.sleep(1);
                }catch (Exception e){

                }
            }
        }).start();

        new Thread(()->{
            fastThreadLocal.set(new Object());
            Object object1 = fastThreadLocal.get();
            System.out.println();

            System.out.println(object1);

        }).start();
    }

}
