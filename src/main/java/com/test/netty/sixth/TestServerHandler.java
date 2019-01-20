package com.test.netty.sixth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {
        //MyDataInfo.Student student =
        System.out.println(msg.getAddress());
        System.out.println(msg.getName());
    }
*/

public class TestServerHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {
        MyMessageInfo.MyMessage.DataType dataType =msg.getDataType();
        if(dataType == MyMessageInfo.MyMessage.DataType.PersonType){
            MyMessageInfo.Person person = msg.getPerson();
            System.out.println(person.getAddress());
            System.out.println(person.getAge());
            System.out.println(person.getName());
        }

        if(dataType == MyMessageInfo.MyMessage.DataType.DogType){
            MyMessageInfo.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());
        }

        if(dataType == MyMessageInfo.MyMessage.DataType.CatType){
            MyMessageInfo.Cat cat = msg.getCat();
            System.out.println(cat.getCity());
            System.out.println(cat.getName());
        }
    }

}
