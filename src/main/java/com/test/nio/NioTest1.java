package com.test.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {

    /**
     * java.io 阻塞的方式
     * java.nio 1.4 非阻塞的方式
     *
     * java.io中最核心的概念就是流Stream，面向流的编程；
     * Java中一个流只能是一种类型，要么输入流，要么输出流，不可能同时既是输入流又是输出流。
     *
     * java.nio中拥有三个核心概念；selector、channel、buffer，在java.nio中，是面向块block或是缓冲区buffer编程的
     * buffer本身就是一块内存，底层实现上它实际上是个数组，数据的读写都是通过buffer来实现的。
     *
     * 除了数组之外，buffer还提供了对于数据的结构化访问方式，并且可以追踪到系统的读写过程
     *
     * Java中的7种原生数据类型都有各自对应的buffer类型，如intbuffer、longbuffer....并没有booleanbuffer类型
     *
     * channel指的是可以向其写入数据或是读取数据的对象，它类似于java.io中的stream
     *
     * 所有数据的读写都是通过buffer来进行的，永远不会出现直接向channel写入数据的情况，或是直接从channel读取数据的情况
     *
     * 与stream不同的是，channel是双向的，一个流只可能是inputstream或者outputstream，channel打开后则可以进行读取、写入或是读写。
     *
     * 由于channel是双向的，因此它能更好的反映出底层操作系统的真实情况；在linux系统中，底层操作系统的通道就是双向的
     *
     *********************************************************************
     * 关于Nio buffer中的三个重要状态属性的含义：position、limit、capacity；
     * 0<=mark<=position<=limit<=capacity
     * buffer 线程不安全
     * 堆内存的分配、堆外内存的分配（操作系统控制）
     *
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        IntBuffer intBuffer1 = IntBuffer.allocate(10);
        for(int i=0;i<intBuffer1.capacity();i++){
            //生成一个随机数，
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer1.put(randomNumber);
        }
        //状态的反转，读写转化，需要调用此方法
        intBuffer1.flip();

        while (intBuffer1.hasRemaining()){
            System.out.println(intBuffer1.get());
        }

        System.out.println("-------------------------------------------");


        IntBuffer intBuffer = IntBuffer.allocate(10);

        System.out.println("capacity:"+intBuffer.capacity());

        for(int i=0;i<5;i++){
            //生成一个随机数，
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);
        }

        System.out.println("before flip limit:"+intBuffer.limit());
        //状态的反转，读写转化，需要调用此方法
        intBuffer.flip();

        System.out.println("after flip limit:"+intBuffer.limit());
        System.out.println("-------------------------------------------");
        while (intBuffer.hasRemaining()){
            System.out.println("position:"+intBuffer.position());
            System.out.println("limit:"+intBuffer.limit());
            System.out.println("capacity:"+intBuffer.capacity());

            System.out.println(intBuffer.get());
        }
    }
}
