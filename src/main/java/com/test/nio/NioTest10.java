package com.test.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {


    /**
     * 文件锁  不常用
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(3,6,true);

        System.out.println("valid:"+fileLock.isValid());

        System.out.println("lock type:"+fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();

    }
}
