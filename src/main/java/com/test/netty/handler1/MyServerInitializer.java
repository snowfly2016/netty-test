package com.test.netty.handler1;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new MyPersonDecoder());
        channelPipeline.addLast(new MyPersonEncode());
        channelPipeline.addLast(new MyServerHandler());
    }
}
