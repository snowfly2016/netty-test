package jdk8.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PersonTest {


    public static void main(String[] args) {

        Person person1 = new Person("zhangsan",20);
        Person person2 = new Person("lisi",40);
        Person person3 = new Person("wangwu",30);

        List<Person> personList = Arrays.asList(person1,person2,person3);

        PersonTest personTest = new PersonTest();

        //
        List<Person> p = personTest.getPersonByUserName("zhangsan",personList);

        p.forEach(person -> System.out.println(person.getUserName()));


        System.out.println("----------------------------------------------------");

        //
        List<Person> ps = personTest.getPersonByAge(20,personList);

        ps.forEach(person -> System.out.println(person.getAge()));


        //传递行为，而不是数据
        List<Person> personList1 = personTest.getPersonByAge1(20,personList,(age,personLists)-> personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList()));
        personList1.forEach(person -> System.out.println(person.getAge()));

        System.out.println("----------------------------------------------------");

        List<Person> personList2 = personTest.getPersonByAge1(20,personList,(age,personLists)-> personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList()));

        personList2.forEach(person -> System.out.println(person.getAge()));
    }


    /**
     * 根据姓名返回指定人
     * @param userName
     * @param personList
     * @return
     */
    public List<Person> getPersonByUserName(String userName,List<Person> personList){
        return personList.
                stream().
                filter(person -> person.getUserName().equals(userName))
                .collect(Collectors.toList());
    }


    public List<Person> getPersonByAge(int age,List<Person> personList){
        BiFunction<Integer,List<Person>,List<Person>> biFunction = (ageOfPerson,personLists)-> personLists.stream().filter(person -> person.getAge() > ageOfPerson).collect(Collectors.toList());

        return biFunction.apply(age,personList);
    }



    public List<Person> getPersonByAge1(int age,List<Person> personList,BiFunction<Integer,List<Person>,List<Person>> biFunction){
        return biFunction.apply(age,personList);
    }

}
