package com.test.thrift;

import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, org.apache.thrift.TException {
        System.out.println("Client 。。。。"+username);
        Person person = new Person();
        person.setAge(20);
        person.setUsername("lz");
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, org.apache.thrift.TException {
        System.out.println("Client。。。。。。");
        System.out.println(person.getAge());
    }
}
