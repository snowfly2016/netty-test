package com.test.decorator;

public class Client {

    /**
     * Java IO 使用的装饰模式
     * 装饰模式又名包装模式(Wrapper)
     * 装饰模式以对客户端透明的方式扩展了对象的功能，是继承关系的一个替代方案
     * 装饰模式以对客户透明的方式动态的给一个对象附加上更多责任。换言之，客户端并不会觉得对象在装饰前和装饰后有什么不同
     * 装饰模式可以在不创造更多子类的情况下，将对象的功能加以扩展；
     * 装饰模式把客户端的调用委派到被装饰类，装饰模式的关键在于这种扩展完全是透明的。
     * 装饰模式是不必改变原来类文件和使用继承的情况下，动态的扩展一个对象的功能。他是通过创建一个包装对象，也就是装饰来包裹真实的对象。
     *
     * 装饰模式的角色
     * 抽象构件角色Component 给出一个抽象接口，以规范准备接受附加责任的对象
     * 具体构件角色Concrete Component 定义一个将要接受附加责任的类
     * 装饰角色Decorator 持有一个构建Component对象的引用，并定义一个与抽象构件接口一致的接口
     * 具体装饰角色Concrete Decorator 负责给构建对象贴上附加的责任
     *
     * 装饰模式的特点：
     * 装饰对象和真实对象有相同的接口。这样客户端对象就可以和真实对象以相同的方式和装饰对象交互
     * 装饰对象包含一个真实对象的引用Reference
     * 装饰对象接受所有来自客户端的请求。他把这些请求转发给真实的对象
     * 装饰对象可以再转发这些请求以前或以后增加一些附加功能。这样就确保了在运行时，不用修改给定对象的结构就可以再外部增加附加的功能，在面向对象的设计中，通常是通过继承来实现对给定类的功能扩展。
     *
     * 装饰模式vs继承
     * 继承
     * 用来扩展一类对象的功能
     * 需要子类
     * 静态
     * 编译是分派职责
     * 导致很多子类产生
     * 缺乏灵活性
     *
     * 装饰模式的适用性
     * 想要透明并且动态地给对象增加新的职责（方法）而又不会影响其他对象
     * 给对象增加的职责在未来可能会发生改变
     * 用子类扩展功能不实际的情况下；
     *
     * 节点流 过滤流
     * @param args
     */
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomthing();

        System.out.println("*********************************************");

        Component component1 = new ConcreteDecorator2(new ConcreteComponent());
        component1.doSomthing();

        System.out.println("------------------------------------------");
        Component component2 = new ConcreteComponent();
        component2.doSomthing();
    }
}
