package com.test.jvm;


public class JvmTest25 implements Runnable{

    private Thread thread;

    public JvmTest25(){
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);

        System.out.println("Class: "+ classLoader.getClass());
        System.out.println("Class: "+ classLoader.getParent().getClass());
    }

    /**
     * Class: class sun.misc.Launcher$AppClassLoader
     * Class: class sun.misc.Launcher$ExtClassLoader
     * 
     * @param args
     */
    public static void main(String[] args) {
        new JvmTest25();
    }
}
