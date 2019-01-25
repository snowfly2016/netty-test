package com.test.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    /**
     * 场景分析：
     * 一个简单的Netty服务端程序，需要实现initializer和handler
     * 可用curl "http://localhost:8899"或者在浏览器中访问该地址
     * 浏览器请求时在调试模式的NetWork中会输出两条网络请求，一条是访问netty服务器的，另外一个是请求获取favicon.ico的。
     * favicon.ico因浏览器而异，有的是没有的。浏览器端访问因为采用的http1.1等会保持连接，不会输出连接断开等信息；
     * 需要等待很长时间才能显示关闭信息；
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //接受连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //服务器启动类 简化netty服务器的创建工作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //属性的装配 方法链编程
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // workergroup -- childHandler 处理逻辑
                    // bossGroup -- handler
                    .childHandler(new TestServerInitializer());
            //服务器启动
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //
            channelFuture.channel().closeFuture().sync();

        }finally {
            //关闭操作
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
