package com.test.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        //自定义解码器
        //channelPipeline.addLast(new MyByteToLongDecoder());
        channelPipeline.addLast(new MyByteToLongDecoder1());
        channelPipeline.addLast(new MyByteToLongEncoder());
        channelPipeline.addLast(new MyLongToStringDecoder());
        channelPipeline.addLast(new MyServerHandler());
    }
}
