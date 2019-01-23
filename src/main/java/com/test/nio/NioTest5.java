package com.test.nio;

import java.nio.ByteBuffer;

public class NioTest5 {

    /**
     * buffer
     * 类型匹配
     * 放置什么类型，取出来时也是什么类型 否则报错
     * 类型化的put与get方法
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(15);
        byteBuffer.putLong(50000000L);
        byteBuffer.putChar('你');
        byteBuffer.putDouble(14.1211221);
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('我');

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());

    }
}
