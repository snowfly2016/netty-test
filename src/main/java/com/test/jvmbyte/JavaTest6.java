package com.test.jvmbyte;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理字节码层面的实现
 *
 * ProxyClassFactory
 * generateProxyClass
 *
 */
public class JavaTest6 {

    public static void main(String[] args) {
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        RealSubject realSubject = new RealSubject();

        InvocationHandler invocationHandler = new DynamicSubject(realSubject);

        Class<?> c = realSubject.getClass();

        Subject subject =(Subject) Proxy.newProxyInstance(c.getClassLoader(),c.getInterfaces(),invocationHandler);

        subject.request();

        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass());
    }

}
