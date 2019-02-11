package com.test.netty.handlermsg;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count ;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes=new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        String message = new String(bytes,Charset.forName("utf-8"));

        System.out.println("MSG context:"+message);
        System.out.println("MSG count:"+(++this.count));

        ByteBuf res = Unpooled.copiedBuffer(UUID.randomUUID().toString(),Charset.forName("utf-8"));

        ctx.writeAndFlush(res);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
