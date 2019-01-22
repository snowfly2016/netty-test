package com.test.decorator;

public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomthing() {
        super.doSomthing();
        this.doAnotherthing();
    }

    private void doAnotherthing(){
        System.out.println("Function C");
    }
}
