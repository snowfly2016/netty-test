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
     * 编解码 乱码产生的根源
     *
     * 查看文件编码的方式
     * enca工具
     *
     * 存储一定是字节方式的，而不是字符，字符只是便于程序的处理而已
     *
     * 1.iso-8859-1中文正常可以输出？平时有可能为啥会乱码？
     * 源文件utf-8
     * 2.utf-8 utf-16 utf-32 utf-16LE utf-16BE uinicode iso-8859-1 ASCII
     * 3.LE、BE
     * 4.iso-8859-1 ASCII关系
     * 5.
     *******************************************************************************
     * 1.ASCII 美国信息交换标准代码 American Standard Code for Inforamtion Interchange
     *   7 bit来表示一个字符，共计可以表示128中字符(剩余一位未利用)
     *
     * 2.ISO-8859-1
     *  8 bit 表示一个字符，即用一个字节byte（8bit）来表示一个字符，共计可以表示256字符；所有位充分利用，向下完全兼容ascii
     *
     * 3.gb2312(中国)
     *  两个字节表示一个汉字，未考虑生僻汉字。16bit
     *
     * 4.gbk(中国)
     *  包含生僻汉字 gb2312的超集
     *
     * 5.gb18030(中国) 全集
     *
     * 6.big5（台湾）
     *
     * ................
     *
     * 7.unicode    采用了两个字节来表示一个字符 (存储英文国家的文件会造成空间膨胀)  最广泛最全的
     *
     * 8.UTF（unicode translation format）
     *   unicode是一种编码方式，而UTF则是一种存储方式，UTF-8是unicode的实现方式之一。
     *   UTF-16le（little endian）小端
     *   UTF-16be(big endian) 大端
     *   Zero Width No-Break Space, 0xFEFF(BE)   0xFFFF(LE) 与计算的存储架构相关;
     *
     *   UTF-8 变成字节表示形式；一般来说，UTF-8会通过三个字节来表示一个中文。
     *
     *   BOM（Byte Order Mark) 字节顺序标记,   window存储有可能会带BOM需要注意;
     *
     *
     *
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


        System.out.println("*******************************");
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k+"   +  "+v);
        });
        System.out.println("*******************************");

        //Charset charset = Charset.forName("utf-8");

        // 中文为啥正确输出？？？
        Charset charset = Charset.forName("iso-8859-1");
        //
        CharsetDecoder charsetDecoder = charset.newDecoder();
        //
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //解码 将一个文件中的内容转化为字符
        CharBuffer charBuffer = charsetDecoder.decode(mappedByteBuffer);

        System.out.println(charBuffer.get(11));
        //编码 把字符转化为字节
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        //ByteBuffer byteBuffers = Charset.forName("UTF-8").encode(charBuffer);

        fileChannel1.write(byteBuffer);

        randomAccessFile.close();
        randomAccessFile1.close();

    }


}
