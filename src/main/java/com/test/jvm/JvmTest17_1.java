package com.test.jvm;

/**
 * 关于命名空间的重要说明：
 * 1.子加载器所加载的类能够访问父加载器所加载的类
 * 2.父加载器所加载的类无法访问子加载器所加载的类
 *
 *
 *
 */
public class JvmTest17_1 {


    /**
     *
     * 删除MyCat.class文件
     * class: 1067040082
     * MySample is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
     * Exception in thread "main" java.lang.NoClassDefFoundError: com/test/jvm/MyCat
     * 	at com.test.jvm.MySample.<init>(MySample.java:8)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     * 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     * 	at java.lang.Class.newInstance(Class.java:442)
     * 	at com.test.jvm.JvmTest17_1.main(JvmTest17_1.java:15)
     * Caused by: java.lang.ClassNotFoundException: com.test.jvm.MyCat
     * 	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
     * 	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
     * 	... 7 more
     *
     * 删除MySample.class文件
     * findClass invoked com.test.jvm.MySample
     * class loader name loader1
     * class: 980546781
     * MySample is loaded by: JvmTest16{classLoaderName='loader1'}
     * MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
     *
     * 删除MySample.class、MyCat.class文件
     *
     *
     * MyCat add System.out.println("from MyCat: "+ MySample.class);
     *
     * findClass invoked com.test.jvm.MySample
     * class loader name loader1
     * class: 980546781
     * MySample is loaded by: JvmTest16{classLoaderName='loader1'}
     * MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
     * Exception in thread "main" java.lang.NoClassDefFoundError: com/test/jvm/MySample
     * 	at com.test.jvm.MyCat.<init>(MyCat.java:10)
     * 	at com.test.jvm.MySample.<init>(MySample.java:8)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     * 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     * 	at java.lang.Class.newInstance(Class.java:442)
     * 	at com.test.jvm.JvmTest17_1.main(JvmTest17_1.java:46)
     * Caused by: java.lang.ClassNotFoundException: com.test.jvm.MySample
     * 	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
     * 	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
     * 	... 8 more
     *
     * MySample add System.out.println("from MySample: "+ MyCat.class);
     * findClass invoked com.test.jvm.MySample
     * class loader name loader1
     * class: 980546781
     * MySample is loaded by: JvmTest16{classLoaderName='loader1'}
     * MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
     * Exception in thread "main" java.lang.NoClassDefFoundError: com/test/jvm/MySample
     * 	at com.test.jvm.MyCat.<init>(MyCat.java:10)
     * 	at com.test.jvm.MySample.<init>(MySample.java:8)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
     * 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     * 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     * 	at java.lang.Class.newInstance(Class.java:442)
     * 	at com.test.jvm.JvmTest17_1.main(JvmTest17_1.java:81)
     * Caused by: java.lang.ClassNotFoundException: com.test.jvm.MySample
     * 	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
     * 	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
     * 	... 8 more
     *
     * 	MyCat 注释掉System.out.println("from MyCat: "+ MySample.class);
     * findClass invoked com.test.jvm.MySample
     * class loader name loader1
     * class: 980546781
     * MySample is loaded by: JvmTest16{classLoaderName='loader1'}
     * MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
     * from MySample: class com.test.jvm.MyCat
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        JvmTest16 jvmTest16 = new JvmTest16("loader1");

        jvmTest16.setPath("/Users/elm/Desktop/");
        Class<?> classz = jvmTest16.loadClass("com.test.jvm.MySample");
        System.out.println("class: "+classz.hashCode());

        //如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用，因此不会实例化MyCat对象，
        // 即没有对MyCat进行主动使用，这里就不会加载MyCat Class（注意：这里未必不会加载噢）
        Object object = classz.newInstance();
    }


}
