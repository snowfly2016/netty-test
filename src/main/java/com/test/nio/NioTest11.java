package com.test.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest11 {

    /**
     *
     * 关于buffer的 scattering 与 gathering
     * 分散 聚集
     * read、write
     * scattering 传递一个buffer数组 把来自一个channel读到一个buffer，顺序来读；先把第一个buffer读满，在读第二个...；
     * gathering 传递一个buffer数组 依次把buffer中的数据写入到一个channel中，顺序写；先写第一个，在写第二个...；
     * 用途：
     *
     * 客户端和服务器端示例 网络程序
     * mac  nc localhost 8899 hellowor 注意回车算一个字符
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(inetSocketAddress);

        //三个字节数组
        int messageLength = 2+3+4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] =ByteBuffer.allocate(2);
        byteBuffers[1] =ByteBuffer.allocate(3);
        byteBuffers[2] =ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true){
            int bytesRead = 0;
            //不停的读取
            while (bytesRead < messageLength){
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;

                System.out.println("bytesRead: "+bytesRead);

                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position: "+byteBuffer.position()+" ,limit: "+byteBuffer.limit()).forEach(System.out::println);

            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            //写数据
            long bytesWritten =0;
            while (bytesWritten < messageLength){
                long r = socketChannel.write(byteBuffers);
                bytesWritten +=r;
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

            System.out.println("bytesRead: "+bytesRead+",bytesWritten: "+bytesWritten+",messageLength:"+messageLength);
        }

    }


}
