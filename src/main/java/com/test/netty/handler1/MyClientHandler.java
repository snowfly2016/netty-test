package com.test.netty.handler1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] bytes = msg.getContent();

        System.out.println("client ------------------------------");
        String message = new String(bytes,Charset.forName("utf-8"));
        System.out.println("client length:"+length);
        System.out.println("client context:"+message);
        System.out.println("client count:"+(++this.count));

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++){
            String messageToBeSend ="sent from client ";
            byte[] content = messageToBeSend.getBytes(Charset.forName("utf-8"));
            int length = messageToBeSend.getBytes(Charset.forName("utf-8")).length;

            PersonProtocol personProtocol = new PersonProtocol();

            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
