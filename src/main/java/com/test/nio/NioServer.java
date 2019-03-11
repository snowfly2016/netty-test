package com.test.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String,SocketChannel> map = new HashMap<>();
    /**
     * 一个服务端，多个客户端 多客户端通信
     *
     * 一个线程解决了多客户端问题
     *
     * 服务器端保存连接信息
     *
     * 启动N个客户端；NC客户端可以进行测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel =  ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        //
        Selector selector = Selector.open();
        //注册
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();
                //获取事件集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel socketChannel;
                    try{
                        if(selectionKey.isAcceptable()){
                            //为啥是ServerSocketChannel？向下类型转换成功？
                            ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                            socketChannel = server.accept();
                            socketChannel.configureBlocking(false);
                            //注册读事件
                            socketChannel.register(selector,SelectionKey.OP_READ);

                            String key = "{"+UUID.randomUUID().toString()+"}";
                            map.put(key,socketChannel);

                        }else if(selectionKey.isReadable()){
                            //
                            socketChannel = (SocketChannel)selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count =socketChannel.read(readBuffer);

                            if(count > 0){
                                readBuffer.flip();

                                Charset charset =Charset.forName("UTF-8");
                                String receiverMessage = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println("receiverMessage:"+receiverMessage);

                                String senderKey = null;
                                // 获取发送者的key
                                for(Map.Entry<String,SocketChannel> entry:map.entrySet()){
                                    if(socketChannel == entry.getValue()){
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }
                                // 将消息发送给其他客户端
                                for(Map.Entry<String,SocketChannel> entry:map.entrySet()){

                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((senderKey +":"+receiverMessage).getBytes());

                                    writeBuffer.flip();

                                    value.write(writeBuffer);
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                //处理完成后，需要清空操作
                selectionKeys.clear();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
