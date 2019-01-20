package com.test.netty.fourth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ChannelInboundHandlerAdapter
 * 适配器
 *
 *
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx
     * @param evt 事件对象
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            switch (event.state()){
                case READER_IDLE:
                    eventType ="read 空闲";
                    break;
                case WRITER_IDLE:
                    eventType ="write 空闲";
                    break;
                case ALL_IDLE:
                    eventType ="read/write 空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+" 超时事件 "+eventType);
            ctx.channel().close();
        }
    }
}
