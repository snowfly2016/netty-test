package lb.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 这里是重入锁的另外一种情况，继承
 */
public class Demo13 {

    synchronized void test(){
        System.out.println("demo test start........");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("demo test end........");
    }

    public static void main(String[] args) {
        new Demo13().test();
    }
}
class Demo extends Demo13 {

    @Override
    synchronized void test(){
        System.out.println("demo test start........");
        super.test();
        System.out.println("demo test end........");
    }

}