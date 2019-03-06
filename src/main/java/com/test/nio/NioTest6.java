package com.test.nio;

import java.nio.ByteBuffer;

public class NioTest6 {


    /**
     * slice buffer 与原有buffer共享相同的底层数据;如果对原来的buffer修改，则会反应到新生成的buffer上；反之亦然；
     * 但是两个buffer有各自的position 和limit
     *
     * shift+esc 关闭terminal
     * tab键删除原来方法
     *
     * 以下代码是修改了新的buffer，底层的数据页发生了变化；反映到原来的buffer上了
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for(int i=0;i<10;i++){
            byteBuffer.put((byte) i);
        }


        //slice
        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer buffer = byteBuffer.slice();

        for(int i=0;i<buffer.capacity();i++){
            byte b = buffer.get(i);
            b*=2;
            buffer.put(i,b);
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }

    }


}
