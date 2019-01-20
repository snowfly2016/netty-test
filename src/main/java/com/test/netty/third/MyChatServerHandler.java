package com.test.netty.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    ////保存channel对象
   private  static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel= ctx.channel();
        //找出自己
        channelGroup.forEach(ch ->{
            if(channel != ch){
                ch.writeAndFlush(channel.remoteAddress()+"send information:"+msg+"\n");
            }else {
                ch.writeAndFlush("{myself}"+msg+"\n");
            }
        });

    }

    /**
     * 连接建立 广播信息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ////先广播，然后加入
        channelGroup.writeAndFlush("{服务器}-"+channel.remoteAddress()+" 加入\n");

        channelGroup.add(channel);
    }

    /**
     * 连接断开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel= ctx.channel();

        channelGroup.writeAndFlush("{服务器}-"+channel.remoteAddress()+" 离开\n");
        //根据数量判断 是否自动断丢的会被自动剔除
        System.out.println(channelGroup.size());

        //自动寻找断掉的，移除，无须手动触发
        //channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel= ctx.channel();
        System.out.println(channel.remoteAddress()+"online\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel= ctx.channel();
        System.out.println(channel.remoteAddress()+"offline\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
