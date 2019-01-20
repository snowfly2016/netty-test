package com.test.netty.sixth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/*public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {

    }*/
public class TestClientHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int random = new Random().nextInt(3);
        MyMessageInfo.MyMessage myMessage = null;
        if(random == 0){
            myMessage = MyMessageInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyMessageInfo.Person.newBuilder()
                            .setAddress("bj ta").setAge(20).setName("xxxx").build()).build();
        }else if(random == 1){
            myMessage = MyMessageInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageInfo.MyMessage.DataType.DogType)
                    .setDog(MyMessageInfo.Dog.newBuilder()
                            .setAge(21).setName("111xxxx").build()).build();
        }else {
            myMessage = MyMessageInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageInfo.MyMessage.DataType.CatType)
                    .setCat(MyMessageInfo.Cat.newBuilder()
                            .setCity("America").setName("111111").build()).build();
        }

        ctx.channel().writeAndFlush(myMessage);

        /*MyDataInfo.Student student= MyDataInfo.Student.newBuilder().setAddress("11").setAge(2).setName("sss").build();

        ctx.channel().writeAndFlush(student);*/


    }
}
