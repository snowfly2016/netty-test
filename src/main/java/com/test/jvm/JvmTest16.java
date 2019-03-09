package com.test.jvm;

import java.io.*;

/**
 * 自定义类加载器
 * Class objects for array classes are not created by class loaders, but are created automatically as required by the Java runtime.
 * The class loader for an array class, as returned by Class.getClassLoader() is the same as the class loader for its element type;
 * if the element type is a primitive type, then the array class has no class loader.
 *
 * 类的卸载：
 * 当MySample类被加载、连接、初始化后，它的生命周期就开始了。当代表MySample类的class对象不再被引用，即不可触及时，Class对象就会结束生命周期，MySample类在方法区内的数据也会被卸载，从而结束MySample类的生命周期；
 *
 * 一个类何时技术生命周期，取决于代表它的class对象何时结束生命周期；
 *
 * 由Java虚拟机自带的类加载器所加载的类，在虚拟机的生命周期中，始终不会被卸载。
 * Java虚拟机自带的类加载器包括根类加载器、扩展类加载器和系统类加载器。
 * Java虚拟机本身会始终引用这些类加载器，而这些类加载器则会始终引用他们所加载的类的class对象；因此这些class对象始终是可触及的。
 *
 * 由用户自定义的类加载器所加载的类是可以被卸载的；
 *
 * 在类加载器的内部实现中，用一个Java集合来存放所加载的类引用。另一方面，一个class对象总是会引用它的类加载器，调用Class对象的getClassLoader()方法，就能获得它的类加载器。一个类的实例总是引用代表这个类的Class对象，在Object类中定义了getClass()方法，这个方法返回代表所属类的Class对象的引用。此外，所有的Java类都有一个静态属性class,他引用代表这个类的Class对象。
 *
 *
 *
 *
 *
 */
public class JvmTest16 extends ClassLoader {

    private String classLoaderName ;

    private final String fileExtension =".class";

    private String path;

    public JvmTest16(String classLoaderName){
        //将系统类加载器当做该类加载器的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public JvmTest16(String classLoaderName,ClassLoader parent){
        //显式指定该类的父类加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public JvmTest16(ClassLoader parent){
        //显式指定该类的父类加载器
        super(parent);
    }

    @Override
    public String toString() {
        return "JvmTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    /**
     * 寻找拥有指定二进制类的名字
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass invoked "+name);
        System.out.println("class loader name "+this.classLoaderName);
        //io操作
        byte[] data = this.loadClassData(name);
        //委托返回类
        return this.defineClass(name,data,0,data.length);
    }

    private byte[] loadClassData(String className){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            className = className.replace(".","/");
            is = new FileInputStream(new File(this.path + className+ this.fileExtension));

            byteArrayOutputStream = new ByteArrayOutputStream();

            int ch = 0;

            while (-1 !=(ch = is.read())){
                byteArrayOutputStream.write(ch);
            }
            data = byteArrayOutputStream.toByteArray();

        }catch (Exception e){

        }finally {
            try{
                is.close();
                byteArrayOutputStream.close();

            }catch (Exception e){

            }
        }

        return data;
    }


    public static void test(ClassLoader classLoader)throws Exception{
        Class<?> classz = classLoader.loadClass("com.test.jvm.JvmTest1");
        Object object = classz.newInstance();
        System.out.println("---------------------------");
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
    }

    /**
     *
     * 自定义的类加载器为啥没有生效？
     *
     * 类加载器 命名空间
     * 每个类加载器都有自己的命名空间，命名空间由该类加载器及所有父加载器所加载的类组成
     * 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
     * 在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类
     *
     * 在out目录中将JvmTest1.class文件删除，在桌面建立一个路径，
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        JvmTest16 jvmTest16 = new JvmTest16("loader1");
        System.out.println(jvmTest16.getClass().getClassLoader());
        //test(jvmTest16);


        System.out.println("----------------new----------------");
        //jvmTest16.setPath("/Users/elm/Documents/project/netty-test/out/production/classes/");

        jvmTest16.setPath("/Users/elm/Desktop/");
        Class<?> classz = jvmTest16.loadClass("com.test.jvm.JvmTest1");
        System.out.println("class:"+classz.hashCode());
        Object object = classz.newInstance();
        System.out.println(object);

        /*System.out.println("-------------new1-----------------------");
        //JvmTest16 jvmTest161 = new JvmTest16("loader2");
        JvmTest16 jvmTest161 = new JvmTest16("loader2",jvmTest16);
        jvmTest161.setPath("/Users/elm/Desktop/");
        Class<?> classz1 = jvmTest161.loadClass("com.test.jvm.JvmTest1");
        System.out.println("class:"+classz1.hashCode());
        Object object1 = classz1.newInstance();
        System.out.println(object1);

        System.out.println("-------------new2-----------------------");
        //JvmTest16 jvmTest161 = new JvmTest16("loader3");
        JvmTest16 jvmTest162 = new JvmTest16("loader2",jvmTest161);
        jvmTest161.setPath("/Users/elm/Desktop/");
        Class<?> classz2 = jvmTest162.loadClass("com.test.jvm.JvmTest1");
        System.out.println("class:"+classz2.hashCode());
        Object object2 = classz2.newInstance();
        System.out.println(object2);*/

        //验证类卸载 jvisualvm
        /*jvmTest16 = null;
        classz = null;
        object = null;

        System.gc();

        Thread.sleep(20000);

        //类卸载 -XX:+TraceClassUnloading
        jvmTest16 = new JvmTest16("loader1");
        jvmTest16.setPath("/Users/elm/Desktop/");
        classz = jvmTest16.loadClass("com.test.jvm.JvmTest1");
        System.out.println("class:"+classz.hashCode());
        object = classz.newInstance();
        System.out.println(object);*/


    }




    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
