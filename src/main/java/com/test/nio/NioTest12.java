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
 *
 *
 * **********************************************************************
 * NIO能解决传统网络编程存在的问题，
 * Node采用的异步的方式
 *
 * nc localhost 50000
 * nc localhost 50001
 * nc localhost 50002
 * nc localhost 50003
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
        //五个端口号
        int[] ports = new int[5];

        ports[0] =50000;
        ports[1] =50001;
        ports[2] =50002;
        ports[3] =50003;
        ports[4] =50004;

        /**
         * selector 构造 需要<读源码>读源码</读源码>
         * selector 创建方式两种
         * 需要显式调用close()方法去关闭
         *
         * SelectionKey
         *
         * channel register(selector...)操作 一个事件key被加入
         *
         */
        Selector selector = Selector.open();

        /**
         * Selector.open() 的创建者
         * class sun.nio.ch.KQueueSelectorProvider
         * class sun.nio.ch.KQueueSelectorProvider
         */
        System.out.println(SelectorProvider.provider().getClass());
        System.out.println(sun.nio.ch.DefaultSelectorProvider.create().getClass());

        //注册
        for(int i=0;i<ports.length;i++){
            //read ServerSocketChannel 源码
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //配置是否阻塞
            serverSocketChannel.configureBlocking(false);

            //
            ServerSocket serverSocket =serverSocketChannel.socket();
            //绑定
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
            //
            serverSocket.bind(inetSocketAddress);

            //selector注册到channel SelectionKey源码doc阅读
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
                    //selector注册到channel SelectionKey源码doc阅读
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
