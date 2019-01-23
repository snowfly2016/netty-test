package com.test.nio;

import java.nio.ByteBuffer;

public class NioTest6 {


    /**
     * slice buffer 与原有buffer共享相同的底层数据
     *
     * shift+esc 关闭terminal
     * tab键删除原来方法
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for(int i=0;i<10;i++){
            byteBuffer.put((byte) i);
        }


        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer buffer = byteBuffer.slice();

        for(int i=0;i<buffer.capacity();i++){
            byte b = buffer.get(i);
            b*=2;
            buffer.put(i,b);
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }

    }


}
