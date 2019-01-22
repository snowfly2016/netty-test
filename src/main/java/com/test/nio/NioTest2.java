package com.test.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    /**
     * 读取文件
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()){
            byte b = byteBuffer.get();
            System.out.println("character:"+(char)b);
        }
        fileInputStream.available();
    }
}
