package com.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {

    /**
     * 通过NIO读取文件涉及三个步骤
     * 1.从fileinputstream获取到filechannel对象
     * 2.创建buffer
     * 3.将数据从channel读取到buffer中
     *
     * 绝对方法与相对方法
     * 1.相对方法 limit值与position值会在操作时被考虑到
     * 2.绝对方法 完全忽略掉limit值与position方法
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("input.txt");

        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outputFileChannel = fileOutputStream.getChannel();

        //从输入流获取信息，然后写入
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){

            /**
             * ****如果注释掉该行代码，会发生什么呢？ 死循环；*****
             * 第一次：读入数据；read就是实际读入数据的字节数；然后执行filp()方法（limit=p;position指向初始位置);接着执行写入操作后，position和limit指向相同位置；
             * 第二次：如果有clear方法；position指向初始位置，limit指向容量位置；read()方法读入数据到流的末尾，返回-1 结束；如果没有该方法，则此时read()方法不能读入数据返回0；接着执行执行filp()方法；接着执行写入操作后，position和limit指向相同位置；有重复以前的动作；
             */
            byteBuffer.clear();

            int read = inputFileChannel.read(byteBuffer);
            System.out.println("read:" + read);

            if(-1 == read){
                break;
            }

            byteBuffer.flip();

            outputFileChannel.write(byteBuffer);
        }
        inputFileChannel.close();
        outputFileChannel.close();
    }
}
