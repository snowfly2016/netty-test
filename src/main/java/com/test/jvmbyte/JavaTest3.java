package com.test.jvmbyte;

/**
 * 方法的动态分派：
 *
 * 方法的动态分派涉及到一个重要的概念：方法接受者；
 *
 * invokevirtual 字节码指令的多态查找流程；
 * 1.找到操作数栈顶的第一个元素，找到实际的类型；
 * 2.找到后，验证权限等，然后调用相应的方法；
 * 3.验证继承层次继续寻找，如果找不到则抛出异常；
 *
 * 比较方法重载和方法重写，有如下结论：
 * 方法重载是静态的，是编译器行为；
 * 方法重写是动态的，是运行期行为；
 *
 *
 */
public class JavaTest3 {

    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();

        apple.test();
        orange.test();
        apple = new Orange();

        apple.test();
    }
}

class Fruit{

    public void test(){
        System.out.println("Fruit");
    }
}
class Apple extends Fruit{
    @Override
    public void test(){
        System.out.println("Apple");
    }
}
class Orange extends Fruit{
    @Override
    public void test(){
        System.out.println("Orange");
    }
}