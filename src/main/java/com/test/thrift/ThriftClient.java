package com.test.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {

    public static void main(String[] args) {
        /**
         * 客户端与服务器端必须对应协议 TFramedTransport TCompactProtocol
         */
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol tProtocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(tProtocol);

        try {
            tTransport.open();
            Person person = client.getPersonByUsername("lz");
            System.out.println(person.getAge());
            System.out.println(person.getUsername());
            System.out.println(person.isMarried());

            System.out.println("-----------------");
            Person person1 =new Person();
            person1.setMarried(true);
            person1.setUsername("aaa");
                    client.savePerson(person1);

        }catch (Exception e){

        }finally {
            tTransport.close();
        }

    }
}
