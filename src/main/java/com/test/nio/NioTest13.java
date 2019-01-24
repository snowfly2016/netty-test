package com.test.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {


    /**
     * 编解码
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String inputFile ="NioTest13_In.txt";
        String outputFile ="NioTest13_Out.txt";

        RandomAccessFile randomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(outputFile,"rw");

        long length = new File(inputFile).length();

        FileChannel fileChannel = randomAccessFile.getChannel();
        FileChannel fileChannel1 = randomAccessFile1.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,length);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = charsetDecoder.decode(mappedByteBuffer);

        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        fileChannel1.write(byteBuffer);

        randomAccessFile.close();
        randomAccessFile1.close();

    }


}
