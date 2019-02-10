package com.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BytebufTest0 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);

        for(int i=0 ;i<10;i++){
            byteBuf.writeByte(i);
        }

        for(int i=0 ;i<10;i++){
            System.out.println(byteBuf.getByte(i));
        }
    }
}
