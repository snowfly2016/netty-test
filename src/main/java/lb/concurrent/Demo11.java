package lb.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 脏读问题
 * 实际业务当中应该看是否允许脏读，不允许的情况下对读方法也要加锁
 */
public class Demo11 {

    String name;
    double balance;

    public synchronized void set(String name,double balance){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Demo11 demo = new Demo11();
        new Thread(()->demo.set("sk",100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.getBalance("sk"));//

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.getBalance("sk"));
    }
}
