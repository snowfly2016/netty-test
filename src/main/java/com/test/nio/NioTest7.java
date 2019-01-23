package com.test.nio;

import java.nio.ByteBuffer;

public class NioTest7 {


    /**
     *
     * 只读buffer 我们可以随时将一个普通buffer调用asReadOnlyBuffer方法返回一个只读buffer
     * 但不能将一个只读buffer转化为读写buffer
     *
     * class java.nio.HeapByteBuffer
     * class java.nio.HeapByteBufferR
     *
     * heapbytebuffer
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        System.out.println(byteBuffer.getClass());

        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }

        //设置只读
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer();

        System.out.println(byteBuffer1.getClass());

        byteBuffer1.position(0);

        //修改只读buffer 报错 源码直接针对修改抛出异常，可以看看
        //byteBuffer1.put((byte) 1);
    }


}
