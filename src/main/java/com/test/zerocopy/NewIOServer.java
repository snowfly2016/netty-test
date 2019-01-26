package com.test.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class NewIOServer {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{

        InetSocketAddress address = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();
        //启用或者禁用属性 让地址或者端口在超时状态下可重用
        serverSocket.setReuseAddress(true);

        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel =  serverSocketChannel.accept();
            //
            socketChannel.configureBlocking(true);

            int readcount =0;
            while (-1 != readcount){
                try {
                    readcount = socketChannel.read(byteBuffer);

                }catch (Exception e){
                    e.printStackTrace();
                }
                //归位
                byteBuffer.rewind();

            }

        }

    }
}
