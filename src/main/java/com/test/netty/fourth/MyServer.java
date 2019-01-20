package com.test.netty.fourth;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {

    /**
     * 心跳程序
     * 集群间节点通信，tcp
     * 节点之间通过心跳来验证对方是否存活
     * 长连接建立后，客户端应用退出，服务端能感知到对方失去连接；
     * 手机端与服务端建立好长连接，手机端开启飞行或者强制关机，服务端无法感知客户端是否存活，此时推送消息肯定不能到达
     *
     *  使用一下客户端
     *  com.test.netty.third.MyChatClient
     * 理解netty的handler是关键
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
