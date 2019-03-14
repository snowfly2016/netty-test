package com.test.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {

    /**
     * 传统服务器端
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket =new ServerSocket(8899);
        //死循环等等连接
        while (true){
            //阻塞
            Socket socket =serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[4096];
                while (true){
                    //读取
                    int readcount = dataInputStream.read(bytes,0,bytes.length);
                    //根据doc返回-1表示读完了
                    if(-1 == readcount){
                        break;
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
