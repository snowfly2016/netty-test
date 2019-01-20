package com.test.grpc;

import com.test.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * 1.grpc 是一个重要的框架
 * 2.grpc学习曲线很陡峭 需要取做和学习
 * 3.grpc 文件生成Java代码，并且代码不在指定目录下，且没有删除buil下的文件
 * gradle generateProto
 * gradle build 时会报错，因为两份代码，导致问题，如果手动删除了，在执行build，那么还会报错，因为先执行了generateProto有生成了
 * gradle clean build -x test 也不行
 *
 * setOutputSubDir
 * generateProtoTasks.generatedFilesBaseDir
 *
 * 解决问题的思路：关键点
 * 1.去插件的源码包找解决方案
 * 2.搜索问题选择google、stackoverflow
 * 英文必须的study
 * 关键词必须清楚
 * 开发中遇到问题其实不可怕（遇到问题很常见）解决问题的方法、方式、能力衡量一个人技术水平的高低
 * （快慢、方法好坏、发现问题等是人最大的差别）
 *
 * 4.tree工具
 * set nu 展示行号
 * contronl +b/F 上下翻页
 * shift+a 调到行尾
 * :/xxx搜索
 *
 */
public class GrpcClient {


    /**
     *
     * 目前客户端与服务器端建立的是tcp连接
     * 连接复用问题
     * 注意socket编程与http编程的区别
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 对象->对象
         * 同步调用
         */
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8899)
                .usePlaintext(true).build();

        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
                .newBlockingStub(managedChannel);

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc
                .newStub(managedChannel);

        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder()
                .setUsername("zhangSan").build());
        //输出返回结果
        System.out.println(myResponse.getRealname());

        System.out.println("-----------------------------------------------------");

        Iterator<StudentResponse>  iterator= blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

        while (iterator.hasNext()){
            StudentResponse studentResponse = iterator.next();

            System.out.println("result:=="+studentResponse.getAge()+","+studentResponse.getCity()+","+studentResponse.getName());

        }


        System.out.println("****************************------------------------------");
       /* StreamObserver<StudentResponseList> studentResponseListStreamObserver =
                new StreamObserver<StudentResponseList>() {
                    @Override
                    public void onNext(StudentResponseList value) {
                        value.getStudentResponseList().forEach(studentResponse -> {
                            System.out.println(studentResponse.getAge());
                            System.out.println(studentResponse.getCity());
                            System.out.println(studentResponse.getName());
                            System.out.println("@@@@@@@@@@@@@@@@@@");
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("completed");
                    }
                };
        //流式请求必须是异步的方式，不能是同步的方式,因此需要提供异步的方法
        //调用完成后，流程就会继续流转了... 因此不会输出
        StreamObserver<StudentRequest>  studentRequestStreamObserver =stub.getStudentWrapperByAge(studentResponseListStreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());

        studentRequestStreamObserver.onCompleted();
        ////异步的体现
        try {
            Thread.sleep(4000);
        }catch (Exception e){

        }*/

        System.out.println("****************************双向流式数据传递------------------------------");

        StreamObserver<StreamRquest> streamRquestStreamObserver = stub.biTalk(
                new StreamObserver<StreamResponse>() {
                    @Override
                    public void onNext(StreamResponse value) {
                        System.out.println(value.getResponseInfo());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }
                }
        );

        for(int i=0;i<10;i++){
            streamRquestStreamObserver.onNext(StreamRquest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).buildPartial());
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }

        }
    }
}
