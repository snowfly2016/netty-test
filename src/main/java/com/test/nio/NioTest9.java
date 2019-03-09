package com.test.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {

    /**
     * 内存映射文件
     * MappedByteBuffer
     *
     * 在内存中修改文件，不需要操作数据写文件的步骤，修改同步到文件是操作系统来完成的
     * 文件内容本身映射到内存了，不需要跟磁盘交互，只要跟内存操作；
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //随机访问文件
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        //文件通道
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');


        randomAccessFile.close();
    }
}
