package com.test.jvmbyte;

public class ByteTest {

    String str="welcome";

    private int x = 5;

    public static Integer in = 10;

    private synchronized void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        ByteTest byteTest = new ByteTest();

        byteTest.setX(9);

        in = 20;
    }
}
