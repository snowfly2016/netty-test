package com.test.netty.handler1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[]  content = msg.getContent();

        System.out.println("server--------------------------");
        System.out.println("length:"+length);
        System.out.println("content:"+new String(content,Charset.forName("utf-8")));

        System.out.println("server get message count:"+(++this.count));

        String res =UUID.randomUUID().toString();
        int result =res.getBytes("utf-8").length;
        byte[] resp = res.getBytes("utf-8");
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(result);
        personProtocol.setContent(resp);

        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
