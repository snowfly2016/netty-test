package com.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest8 {


    /**
     * allocateDirect  DirectByteBuffer
     *
     * // Used only by direct buffers
     * // NOTE: hoisted here for speed in JNI GetDirectBufferAddress
     * long address; 表示堆外内存分配的地址
     *
     * HeapByteBuffer
     * DirectByteBuffer
     * 这两个根本的地方在于少了一次copy，在高性能的场景下，一次copy是很耗时的
     *
     * 操作系统为何不会去操作堆上的内存呢？如果os操作时，发生了GC，产生了标记压缩回收，对象发生移动，因此数据会发生乱套，那么如果不gc，那么就可能导致oom
     * 解决方案就是不移动对象，综合考量os操作堆代码，不适用，采用copy方案。JNI内存copy，这个JNI copy过程不会发生gc；JVM会保证；
     * 操作系统在拷贝后的地方与io执行交互操作
     *
     * Java heap copy Native heap
     * 这也就是HeapByteBuffer与DirectByteBuffer的区别
     *
     * address维护这个一个引用；
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("input2.txt");

        FileOutputStream fileOutputStream = new FileOutputStream("output2.txt");

        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outputFileChannel = fileOutputStream.getChannel();

        //从输入流获取信息，然后写入
        // wrap() 方法  有两种途径修改数组，1.修改数组 2.修改ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);

        while (true){

            //如果注释掉该行代码，会发生什么呢？
            byteBuffer.clear();

            int read = inputFileChannel.read(byteBuffer);
            System.out.println("read value:" + read);

            if(-1 == read){
                break;
            }

            byteBuffer.flip();

            outputFileChannel.write(byteBuffer);
        }
        inputFileChannel.close();
        outputFileChannel.close();
    }
}
