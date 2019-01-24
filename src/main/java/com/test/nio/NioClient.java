package com.test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) throws IOException {
        try {
            SocketChannel socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();

            socketChannel.register(selector,SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

            while (true){
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for(SelectionKey selectionKey:selectionKeys){
                    if(selectionKey.isConnectable()){
                        SocketChannel channel = (SocketChannel)selectionKey.channel();

                        if(channel.isConnectionPending()){
                            channel.finishConnect();

                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            byteBuffer.put((LocalDateTime.now()+"连接成功").getBytes());

                            byteBuffer.flip();
                            channel.write(byteBuffer);

                            //
                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(()->{
                                while (true){
                                    byteBuffer.clear();
                                    InputStreamReader inputStreamReader = new InputStreamReader(System.in);

                                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                                    String sendMessage = bufferedReader.readLine();

                                    byteBuffer.put(sendMessage.getBytes());
                                    byteBuffer.flip();
                                    channel.write(byteBuffer);
                                }
                            });

                        }
                        channel.register(selector,SelectionKey.OP_READ);

                    }else if(selectionKey.isReadable()){
                        SocketChannel socketChannel1 =(SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int count = socketChannel1.read(byteBuffer);

                        if(count > 0){
                            String rec = new String(byteBuffer.array(),0,count);
                            System.out.println(rec);
                        }


                    }
                }
                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
