package com.test.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    /**
     * 参考grpc官方示例实现服务器端定义
     * 定义文件中参数必须是message类型，不能是int32等
     *
     *
     */
    private Server server;

    private void start()throws IOException {

        this.server =ServerBuilder.forPort(8899)
                .addService(new StudentServiceImpl())
                .build().start();
        System.out.println("grpc server started");

        /**
         * 回调钩子函数
         * 此处可以做一些收尾的工作
         * 阅读addShutdownHook doc文档
         */
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("关闭JVM");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里....");
    }


    private void stop(){
        if(null != this.server){
            this.server.shutdown();

        }
    }


    private void awaitTermination() throws InterruptedException{
        if(null != this.server){
            this.server.awaitTermination();
            //this.server.awaitTermination(3000,TimeUnit.SECONDS);
        }
    }


    public static void main(String[] args) throws Exception{
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        //这个方法必须调用，否则启动后，会自动退出，需要注意
        grpcServer.awaitTermination();
    }
}
