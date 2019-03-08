package com.test.jvm;

public class JvmTest22 {
    static {
        System.out.println("JvmTest22 initializer");
    }

    /**
     * JvmTest22 initializer
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     *
     * 执行如下，为啥还是一样的呢？
     * java -Djava.ext.dirs=./ com.test.jvm.JvmTest22
     *
     * /Users/elm/Documents/project/netty-test/out/production/classes
     * elmdembp:classes elm$ java -Djava.ext.dirs=./ com.test.jvm.JvmTest22
     * JvmTest22 initializer
     * sun.misc.Launcher$AppClassLoader@15db9742
     * sun.misc.Launcher$AppClassLoader@15db9742
     *
     * jar cvf test.jar com.test.jvm.JvmTest22
     * 扩张类加载器需要是jar包模式加载；启动类加载器和应用类加载器只需要class文件
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(JvmTest22.class.getClassLoader());
        System.out.println(JvmTest1.class.getClassLoader());



    }
}
