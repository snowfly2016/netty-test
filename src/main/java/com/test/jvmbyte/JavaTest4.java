package com.test.jvmbyte;

import java.util.Date;

/**
 * 针对于方法调用动态分派的过程，虚拟机会在类的方法区建立一个虚方法表的数据结构（virtual method table,vtable）
 * 针对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法表的数据结构（interface method table itable）
 * 类加载链接阶段完成虚表的初始化
 *
 * 虚方法表中的每一个方法都是被描述为方法调用的入口地址；入口地址是在真正执行时调用；如果子类没有重写父类的方法，换句话说就是直接继承父类的方法，子类的虚方法表不会复制一份方法，而是直接指向了父类的没有被复写的方法；如果子类和父类存在重写的方法，在子类和父类方法在虚表的索引是一致的，也就是知道了子类的索引，直接用该索引能在父类查到，也就节省了查询效率；
 *
 *
 */
public class JavaTest4 {

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.test("hello");
        dog.test(new Date());
    }
}


class Animal{
    public void test(String str){
        System.out.println("animal str");
    }

    public void test(Date date){
        System.out.println("animal date");
    }
}

class Dog extends Animal{
    @Override
    public void test(String str) {
        System.out.println("dog str");
    }

    @Override
    public void test(Date date) {
        System.out.println("dog date");
    }
}