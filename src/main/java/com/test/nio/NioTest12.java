package com.test.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * **传统网络编程**
 * 服务端伪代码
 *
 * ServerSocket serverSocket =.....
 *
 * serverSocket.bind(8899)
 *
 * while(true){
 *     Socket socket = serverSocket.accept();//阻塞方法
 *     //创建一个线程处理，一个连接一个新的线程，此处系统资源有限制，即问题所在...
 *     new Thread(socket);
 *
 *     run(){
 *        socket.getInputStream().
 *       ...
 *       ...
 *       ...
 *     }
 *
 * }
 *
 * 客户端伪代码 8899只提供了连接接口，而数据的传递服务端选择未占用的端口来传递
 *
 * Socket socket = new Socket("localhost",8899)
 *
 * socket.connect();
 *
 * **********************************************************************
 * NIO能解决传统网络编程存在的问题，
 * Node采用的异步的方式
 *
 *
 *
 *
 *
 */
public class NioTest12 {


    /**
     * 服务器端监听五个端口号
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        int[] ports = new int[5];

        ports[0] =50000;
        ports[1] =50001;
        ports[2] =50002;
        ports[3] =50003;
        ports[4] =50004;

        //selector 构造 需要读源码
        Selector selector = Selector.open();

        //Selector.open() 的创建者
        System.out.println(SelectorProvider.provider().openSelector().getClass());
        System.out.println(sun.nio.ch.DefaultSelectorProvider.create().getClass());

        for(int i=0;i<ports.length;i++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);

            ServerSocket serverSocket =serverSocketChannel.socket();

            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);

            serverSocket.bind(inetSocketAddress);

            //
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+ports[i]);

        }

        while (true){
            int numbers = selector.select();
            System.out.println("numbers:"+numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectionKeys:"+selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //移除
                    iterator.remove();

                    System.out.println("获得客户端连接："+socketChannel);
                }else if(selectionKey.isReadable()){

                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    int bytesRead =0;

                    while(true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if(read <= 0){
                            break;
                        }
                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);

                        bytesRead +=read;

                    }

                    System.out.println("读取："+bytesRead+",来自于："+socketChannel);

                    iterator.remove();
                }
            }

        }

    }
}
