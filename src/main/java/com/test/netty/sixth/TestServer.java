package com.test.netty.sixth;

import com.test.netty.first.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestServer {
    /**
     * Q:使用protobuf生成客户端和服务器端使用的文件，如何共享这个文件呢？
     *
     * 使用git作为版本控制系统
     * 方案1-差
     * git submodule git仓库中的一个仓库
     *     ServerProject
     *
     *     Protobuf-Java 中间项目 protobuf 产生文件工程
     *
     *     data.proto
     *
     *     ClientProject
     *  问题：
     *  在版本切换时必须手动切换，因此容易忘记
     *  引用的中间项目是只读的，其他修改，导致问题
     *
     * 方案2-好
     * git subtree
     * 拉取到指定项目，是一个项目，会产生合并指令
     *
     * IDL
     * thrift不支持无符号类型，因为很多编程语言不存在无符号类型
     * 集合中的元素可以是除了Service之外的任何类型，包括exception
     * list
     * set
     * map
     *
     * 数据传输使用socket
     * 支持自定义异常
     *
     */
    /**
     * protocobuf模式下netty客户端和服务端交互
     * 多种数据类型和单个数据类型
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
                    .childHandler(new TestInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
