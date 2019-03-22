package com.test.jvmbyte;

public class ByteTest {

    String str="welcome";

    private int x = 5;

    public static Integer in = 10;

    private synchronized void setX(int x) {
        this.x = x;
    }
    public ByteTest(){

    }
    public ByteTest(int x){

    }
    private void test(String str) {
        synchronized (str){
            System.out.println("hello world");
        }
    }

    private static void test1(String str) {

    }
    public static void main(String[] args) {
        ByteTest byteTest = new ByteTest();

        byteTest.setX(9);

        in = 20;
    }

    static {
        System.out.println("xxxx");
    }
}
