package com.test.jvmbyte;

public class JavaTest {


    public static void main(String[] args) {
        Test test = new Test();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Test.setD1();
            }
        }).start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.setB1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.setA1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.setE1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.setF1();
            }
        }).start();
    }
}

class Test{

    public int a = 1;
    public  Object object = new Object();

    public synchronized void setA1(){
        try {
            System.out.println("A1 waiting。。。。");
            Thread.sleep(1000);
            System.out.println("A1 waiting end。。。。");
        }catch (Exception e){

        }
    }

    public synchronized void setB1(){
        try {
            System.out.println("B1 waiting。。。。");
            Thread.sleep(1000);
            System.out.println("B1 waiting end。。。。");
        }catch (Exception e){

        }
    }

    public void setC1(){
        try {
            System.out.println("C1 waiting。。。。");
            Thread.sleep(1000);
            System.out.println("C1 waiting end。。。。");
        }catch (Exception e){

        }
    }

    public static void setD1(){
        try {
            System.out.println("D1 waiting。。。。");
            Thread.sleep(1000);
            System.out.println("D1 waiting end。。。。");
        }catch (Exception e){

        }
    }

    public void setE1(){
        synchronized (this){
            try {
                System.out.println("E1 waiting。。。。");
                Thread.sleep(1000);
                System.out.println("E1 waiting end。。。。");
            }catch (Exception e){

            }
        }
    }

    public void setF1(){
        synchronized (object){
            try {
                System.out.println("F1 waiting。。。。");
                Thread.sleep(1000);
                System.out.println("F1 waiting end。。。。");
            }catch (Exception e){

            }
        }
    }
}