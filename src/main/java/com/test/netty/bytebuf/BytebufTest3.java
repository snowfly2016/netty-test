package com.test.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class BytebufTest3 {

    /**
     * Netty ByteBuf所提供的3中缓冲区类型：
     * 1.heap buffer
     * 2.direct buffer
     * 3.composite buffer
     **************************************************************
     *
     * Heap Buffer 堆缓冲区
     * 这是最常用的类型，ByteBuf将数据存储到JVM的堆空间中，并且将实际的数据存放到byte array中来实现
     * 优点：由于数据是存储在JVM的堆中，因此可以快速的创建与快速的释放，并且它提供了直接访问内部字节数组的方法
     * 缺点：每次读写数据时，都需要先将数据复制到直接缓冲区中再进行网络传输
     ***************************************************************
     *
     * Direct Buffer 直接缓冲区
     * 在堆之外直接分配内存空间，直接缓冲区并不会占用堆的容量空间，因为他是由操作系统在本地内存进行的数据分配
     * 优点：在使用Socket进行数据传递时，性能非常好，因为数据直接位于操作系统的本地内存中，所以不需要从JVM将数据复制到直接缓冲区中，性能很好
     * 缺点：因为Direct buffer是直接在操作系统内存中的，所以内存空间的分配与释放要比堆空间更加复杂，而且速度要慢一些
     ***************************************************************
     *
     * Netty通过提供内存池来解决这个问题，直接缓冲区并不支持通过字节数据的方式来访问数据；
     * **************************************************************
     *
     * 重点：对于后端的业务消息的编解码来说，推荐使用HeapBuf；对于IO通信线程在读写缓存区时，推荐使用DirectByteBuf。
     * **************************************************************
     *
     * Composite Buffer 复合缓冲区
     * **************************************************************
     *
     * JDK的ByteBuffer与Netty的ByteBuf之间的差异对比：
     *
     * 1.Netty的ByteBuf采用了读写索引分离的策略，（readIndex与writeIndex），一个初始化（里面尚未有任何数据）的ByteBuf的readIndex与writeIndex值都为0；
     * 2.当读索引与写索引处于同一个位置时，如果我们继续读取，那么就会抛出IndexOutOfBoundsException
     * 3.对于ByteBuf的任何读写操作都会分别单独维护读索引与写索引。maxCapacity最大容量默认的限制就是Integer.MAX_VALUE。
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        CompositeByteBuf byteBuf = Unpooled.compositeBuffer();

        ByteBuf heapByteBuf = Unpooled.directBuffer(8);
        ByteBuf directByteBuf = Unpooled.buffer(10);

        byteBuf.addComponents(directByteBuf,heapByteBuf);

        byteBuf.removeComponent(0);

        Iterator<ByteBuf> iterator = byteBuf.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        byteBuf.forEach(System.out::println);
    }
}
