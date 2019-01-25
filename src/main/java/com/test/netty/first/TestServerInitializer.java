package com.test.netty.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //
        ChannelPipeline channelPipeline = ch.pipeline();
        //采用内置的或者自己定义
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("TestHttpServerHandler",new TestHttpServerHandler());
    }
}
