package com.test.jvm;

/**
 *
 */
public class JvmTest13 {

    /**
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$ExtClassLoader@23fc625e
     * null
     *
     * @param args
     */
    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader);

        while (null != classLoader){

            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}

