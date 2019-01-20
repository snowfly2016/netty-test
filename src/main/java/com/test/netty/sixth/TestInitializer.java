package com.test.netty.sixth;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //四个处理器
        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
        ////客户端发送的对象是写死的，此处将会出现问题，如何解决呢？？？两种解决方案
        /**
         * 1.传递消息时，采用自定义协议 前面两位空出来 自定义内容
         * 2.通过消息的定义方式实现 消息嵌套在一个壳子里面
         *
         */
        //channelPipeline.addLast(new ProtobufDecoder(MyDataInfo.Student.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufDecoder(MyMessageInfo.MyMessage.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast(new ProtobufEncoder());

        channelPipeline.addLast(new TestServerHandler());
    }
}
