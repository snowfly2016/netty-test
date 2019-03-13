package com.test.netty.seventh;

public class Test {

/**
 * protobuf 编码解码的库 没有提供传输的服务，
 * thrift
 * GRPC 基于protobuf3 生成客户端服务器端传输消息
 * www.grpc.io
 * payload message 负载体
 * git clone -b v1.2.0 https://github.com/grpc/grpc-java
 *
 * gradlew讲解
 * gradlew =>gradle wrapper
 * 使用者在本地计算上没有安装gradle的情况下，可以通过简单的命令构建项目
 *
 *
 *
 *
 */

/*
    零拷贝：
    将磁盘上文件读取到内存，发送给用户；Linux、Unix操作系统

    User Space                 Kernel Space            Hardware
       |       1.read() syscall    |                      |
       |-------------------------->|       ask for data   |
       |                           |--------------------->|
       |                           |data to kernel buffer |
       |                           |<---------------------|
       |                           |    through DMA       |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |
       |                           |                      |


    用户空间切换到内核空间存在上下文切换；



 */


}
