package com.test.protobuf;

public class ProtobufTest {

    /**
     * 测试protobuf序列化和反序列化
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //将对象转化为字节数据，然后有将字节数组转化为对象
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAddress("bj")
                .setName("zs")
                .setAge(20)
                .build();

        byte[] bytes= student.toByteArray();

        DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);
        System.out.println(student1.getAddress());
        System.out.println(student1.getAge());
        System.out.println(student1.getName());
    }
}
