package lb.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法调用另外一个同步方法，能否得到锁?可以，synchronized本身可支持重入
 */
public class Demo12 {
    synchronized void test1(){
        System.out.println("test1 start.........");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test2();
    }

    synchronized void test2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 start.......");
    }

    public static void main(String[] args) {
        Demo12 demo= new Demo12();
        demo.test1();
    }

}
