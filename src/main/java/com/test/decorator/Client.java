package com.test.decorator;

public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomthing();

        System.out.println("*********************************************");

        Component component1 = new ConcreteDecorator2(new ConcreteComponent());
        component1.doSomthing();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@**********************");
        Component component2 = new ConcreteComponent();
        component2.doSomthing();
    }
}
