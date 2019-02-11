package com.test.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecoder1 extends ReplayingDecoder<Void> {

    /**
     * 自定义协议 header body
     * public class IntegerHeaderFrameDecoder extends ByteToMessageDecoder {
     *
     *       @Override
     *      protected void decode(ChannelHandlerContext ctx,
     *                              ByteBuf buf, List<Object> out) throws Exception {
     *
     *        if (buf.readableBytes() < 4) {
     *           return;
     *        }
     *
     *        buf.markReaderIndex();
     *        int length = buf.readInt();
     *
     *        if (buf.readableBytes() < length) {
     *           buf.resetReaderIndex();
     *           return;
     *        }
     *
     *        out.add(buf.readBytes(length));
     *      }
     *    }
     *    
     * MessageToMessageDecoder
     *
     * 简化字节的数的判断
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder1 decoder invoked");

        out.add(in.readLong());
    }


}
