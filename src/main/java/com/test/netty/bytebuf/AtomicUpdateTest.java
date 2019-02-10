package com.test.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicUpdateTest {

    /**
     * 多线程下，以下代码会出现什么问题？
     * @param args
     */
    public static void main(String[] args) {
        Person person = new Person();

        /*for(int i=0;i<10;i++){
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(20);
                }catch (Exception e){

                }

                System.out.println(person.age++);
            });
            thread.start();
        }*/


        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");

        for(int i=0;i<10;i++){
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(20);
                }catch (Exception e){

                }

                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            });
            thread.start();
        }
    }
}

class Person{

    //int age =1 ;
    volatile int age =1 ;

}