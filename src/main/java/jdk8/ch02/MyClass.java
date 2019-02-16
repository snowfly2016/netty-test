package jdk8.ch02;

public class MyClass extends MyInterfaceImpl implements MyInterface,MyInterface1 {


    /**
     * 关于default方法问题：
     * 1.两个接口中定义了同名的default方法，如果一个实现类实现了这两个接口，那么该实现类会引用那个接口的default方法呢？
     * 答案：实现类编译器直接报错，需要@Override default方法，复写只能覆盖其中一个比如是A接口，那么我想调用B接口的默认方法如何操作呢？
     * B.super.myMethod();
     * 2.如果接口A有实现类C,当前类D继承了C,实现了B,那么D中会使用哪个类的默认方法呢？答案是实现类，因为实现类的优先级高于接口的优先级
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        MyClass myClass =  new MyClass();
        myClass.myMethod();
    }

    @Override
    public void myMethod() {
        //MyInterface1.super.myMethod();
        System.out.println("MyInterface");
    }
}
