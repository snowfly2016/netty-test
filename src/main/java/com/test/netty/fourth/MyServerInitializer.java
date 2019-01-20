package com.test.netty.fourth;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //责任链模式 netty handler

        /**
         * IdleStateHandler 空闲检测
         * handler类似有过滤器和拦截器 ，责任链模式
         * 读时间
         * 写时间
         * 读写时间
         * 触发响应的时间
         *
         */
        channelPipeline.addLast(new IdleStateHandler(5,7,10,TimeUnit.SECONDS));
        //channelPipeline.addLast(new IdleStateHandler(5,7,3,TimeUnit.SECONDS));
        channelPipeline.addLast(new MyServerHandler());

    }
}
