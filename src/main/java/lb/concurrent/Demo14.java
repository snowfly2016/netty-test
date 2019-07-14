package lb.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * t2线程能否执行？
 * 手动上锁，自己释放
 */
public class Demo14 {
    int count = 0;

    synchronized void test(){
        System.out.println(Thread.currentThread().getName() + " start......");
        while (true) {
            count ++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                //碰到异常的情况，如果没有处理，会自动释放锁，所以T2可以执行。
                int i = 1/0;
            }
        }
    }

    public static void main(String[] args) {
        Demo14 demo14 = new Demo14();

        Runnable r = () -> demo14.test();

        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
