package com.test.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void doSomthing() {
        System.out.println("Function A");
    }
}
