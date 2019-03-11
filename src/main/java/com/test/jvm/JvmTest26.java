package com.test.jvm;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器的一般使用模式（获取 使用 还原）
 * ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 * try{
 *     Thread.currentThread().setContextClassLoader(targetTccl);//targetTccl目标的classloader
 *     myMethod();
 * }finally{
 *     Thread.currentThread().setContextClassLoader(classLoader);
 * }
 * myMethod()里面则调用了Thread.currentThread().getContextClassLoader()获取当前线程的上下文类加载器做某些事情
 *
 * 如果一个类有类加载器A加载，那么这个类的依赖类也是有相同的类加载器加载的（如果该依赖类之前没有被加载过的话）
 *
 * ContextClassLoader的作用就是为了破坏Java的类加载委托机制
 *
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（实例化）底层的类时，就必须通过线程上下文类加载器来帮助高层的ClassLoader找到并加载该类；
 *
 *
 */
public class JvmTest26 {

    /**
     * driver:class com.mysql.jdbc.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * driver:class com.mysql.fabric.jdbc.FabricMySQLDriver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
     * 当前线程上下文加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
     * serviceLoader类加载器：null
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        //修改线程上下文类加载器；
        //Thread.currentThread().setContextClassLoader(JvmTest26.class.getClassLoader().getParent());



        //Driver 是一个接口，如何找到具体的实现类呢？ 阅读ServiceLoader doc
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        //
        Iterator<Driver> iterator = serviceLoader.iterator();

        while (iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("driver:"+driver.getClass()+",loader:"+driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文加载器："+Thread.currentThread().getContextClassLoader());
        System.out.println("serviceLoader类加载器："+ServiceLoader.class.getClassLoader());
    }
}
