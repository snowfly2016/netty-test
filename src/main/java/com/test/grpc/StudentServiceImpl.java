package com.test.grpc;

import com.test.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     * 方法返回是void 如何返回客户端呢? 关键在于StreamObserver<MyResponse> responseObserver
     * 此处与定义文件不同，需要注意
     * @param request
     * @param responseObserver 返回客户端信息
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受到客户端信息："+request.getUsername());
        //构建返回对象 回调方法，
        responseObserver.onNext(MyResponse.newBuilder().setRealname("zhangSan").build());
        //结束方法调用
        responseObserver.onCompleted();
    }


    /**
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接受到客户端信息："+request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("zhangsan").setAge(20).setCity("bj").build());

        responseObserver.onNext(StudentResponse.newBuilder().setName("lisi").setAge(20).setCity("bj").build());

        responseObserver.onNext(StudentResponse.newBuilder().setName("wangwu").setAge(20).setCity("bj").build());

        responseObserver.onNext(StudentResponse.newBuilder().setName("zhaosi").setAge(20).setCity("bj").build());

        //结束方法调用
        responseObserver.onCompleted();

    }


    /**
     * 返回类型与前面不同...
     *
     * 通过回调实现参数返回
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        System.out.println("****************************");


        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext:"+value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            /**
             * 客户端流数据全部发送给服务器端完成后，服务器端返回结果给客户端，
             */
            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("zhangsan").setCity("NJ").setAge(20).build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("lisi").setCity("NJ").setAge(20).build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse).addStudentResponse(studentResponse2).build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }


    /**
     * 流双方是独立，但是双方都应有关闭操作
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StreamRquest> biTalk(StreamObserver<StreamResponse> responseObserver) {

        return new StreamObserver<StreamRquest>() {
            @Override
            public void onNext(StreamRquest value) {
                System.out.println(value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder()
                        .setResponseInfo(UUID.randomUUID().toString())
                        .build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
