package com.test.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

public class ThriftServer {

    /**
     * thrift netty
     * 如何解决 thrift --gen java xxx/xxxx 生成类中包含gen-java
     *
     * 传输格式
     * TBinaryProtocol 二进制格式
     * TCompactProtocol 压缩格式
     * TJSONProtocol JSON格式
     * TSimpleJSONProtocol 提供json只写协议，生成的文件很容易通过脚本语言解析，（无法通过程序很容易读取，缺少元数据，解析困难）
     * TDebugProtocol 使用易懂的可读文件格式，以便于调试
     *
     * 传输方式
     * TSocket 阻塞式socket
     * TFramedTransport 以frame为单位进行传输，非阻塞式服务中使用
     * TFileTransport 以文件形式进行传输
     * TMemoryTransport 将内存用于I/O，Java实现时内部实际使用了简单的ByteArrayOutputStream
     * TZlibTransport 使用zlib进行压缩，与其他传输方式联合使用，当前无Java实现
     *
     * 服务模型
     * TSimpleServer 简单的单线程服务模型，常用于测试
     * TThreadPoolServer 多线程服务模型，使用标准的阻塞式IO
     * TNonblockingServer 多线程服务模型，使用非阻塞式IO(需使用TFramedTransport数据传输方式)
     * THsHaServer THsHa引入了线程池去处理，其他模型把读写任务放到线程池去处理，Half-sync、Half-async的处理模式，Half-async
     * 是在处理IO事件上（accept read wirte io），Half-sync用于handler对rpc的同步处理
     *
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        /**
         * 一下的搭配在高并发时选型还是很给力的
         */
        TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(8899);
        THsHaServer.Args args1 = new THsHaServer.Args(tNonblockingServerSocket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> personServiceProcessor = new PersonService.Processor<>(new PersonServiceImpl());
        // 协议层
        args1.protocolFactory(new TCompactProtocol.Factory());
        //传输层
        args1.transportFactory(new TFramedTransport.Factory());

        args1.processorFactory(new TProcessorFactory(personServiceProcessor));
        //
        TServer tServer = new THsHaServer(args1);
        System.out.println("thrift server started!");
        // 死循环 永远不会退出
        tServer.serve();
    }
}
