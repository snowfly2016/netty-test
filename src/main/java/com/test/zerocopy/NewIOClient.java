package com.test.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception{

        String fileName ="/xxx/xxx.pptx";

        SocketChannel socketChannel =SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost",8899));

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long starttime = System.currentTimeMillis();

        // 文件传递 ?? 零拷贝
        /**
         *  * <p> This method is potentially much more efficient than a simple loop
         *      * that reads from the source channel and writes to this channel.  Many
         *      * operating systems can transfer bytes directly from the source channel
         *      * into the filesystem cache without actually copying them.  </p>
         */
        long transfercount = fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("发送总字节数："+transfercount+",消耗时间："+(System.currentTimeMillis()-starttime));

    }
}
