package com.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {

    /**
     * 通过nIO读取文件涉及三个步骤
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
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){

            //如果注释掉该行代码，会发生什么呢？
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
