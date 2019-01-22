package com.test.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomthing() {
        super.doSomthing();
        this.doAnotherthing();
    }

    private void doAnotherthing(){
        System.out.println("Function B");
    }
}
