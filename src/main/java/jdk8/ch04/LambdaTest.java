package jdk8.ch04;

public class LambdaTest {

    Runnable r1 = ()-> System.out.println(this);
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    /**
     * 比较输出结果
     * 当前类+匿名类顺序${1....N}
     *
     * 匿名类 在类内部开辟一个新的作用域，与外层类的作用域不是同一个
     * lambda 外层作用域 并不是内部类的一个语法糖或者表现形式，根本上不是一回事，只是能完成相同的功能
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();

        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();
        System.out.println("-----------------------");

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();


    }
}
