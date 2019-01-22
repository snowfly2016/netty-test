package com.test.decorator;

public class Decorator implements Component {

    private  Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomthing() {
        component.doSomthing();
    }
}
