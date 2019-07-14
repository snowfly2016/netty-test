package lb.concurrent;

public class Demo9 implements Runnable{

    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            //相比较Demo1，这里是new了五个对象，每个线程对应都拿到各自的锁标记，可以同时执行。
            Demo9 demo = new Demo9();
            new Thread(demo,"THREAD" + i).start();
        }
    }

}
