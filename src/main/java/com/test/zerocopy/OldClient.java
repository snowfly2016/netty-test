package com.test.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",8899);

        String fileName ="/XXX/xx/xx.pptx";

        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];

        long readcount ;

        long toltal =0;

        long starttime = System.currentTimeMillis();

        while ((readcount =inputStream.read(buffer))>=0){

            toltal += readcount;
            dataOutputStream.write(buffer);


        }

        System.out.println("发送总字节数："+toltal+",耗时："+(System.currentTimeMillis()-starttime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();

    }
}
