package com.test.netty.second;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {

    /**
     * 客户端发送信息给服务端，服务端推送信息给客户端
     * 基于tcp双向通信
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        /**
         * 1.为何创建两个group
         * 2.接受用户端请求 bossGroup
         * 3.处理用户端请求 workerGroup
         *
         * EventLoopGroup 事件循环组 死循环
         * NioEventLoopGroup  异步的事件循环组 底层死循环
         *
         * EventLoopGroup bossGroup = new NioEventLoopGroup(1); 1代表一个线程；核心数*2
         *
         * 作用：完成准备工作 属性的赋值操作
         * ThreadFactory 创建线程
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 辅助类
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /**
             * 方法链编程
             * group 赋值操作 注意类转化
             * channel 方法接受.class 大部分与反射有关
             *
             *
             */
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.WARN))
                    .childHandler(new MyServerInitializer());
            //服务器的启动 逻辑梳理 关键
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            //
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    /**
     1.为何创建两个group；接受请求bossgroup，处理请求workergroup
     EventLoopGroup 事件循环组 一个死循环；
     EventLoopGroup 在事件循环的过程中（事件发生、连接建立），在selector选择时，允许注册channel（客户端连接等）；
     EventLoop 事件循环

     ChannelFuture
     ChannelPromise 包含了channel的引用

     线程数默认值为1；
     线程的创建与任务解耦

     命令模式、代理模式 ThreadPerTaskExecutor
     Executor源码 doc












     */
}
