package jdk8.ch01;

import java.util.function.Function;

public class FunctionTest {

    /**
     *  高阶函数：如果一个函数接收一个函数作为参数，或者返回一个函数作为返回值，那么该函数就叫高阶函数
     *
     *  <V> Function<V, R> compose(Function<? super V, ? extends T> before)  方法
     *  组合函数，两个function组合，先调用before，在调用当前的function；相当于多个function的串联调用
     *
     *  <V> Function<T, V> andThen(Function<? super R, ? extends V> after) 方法
     *  与上面的相反，先应用当前函数function，然后应用after function
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        FunctionTest functionTest = new FunctionTest();

        //传递的是行为,用户在执行的时候传递行为，
        System.out.println(functionTest.compute(1,item->{return 2 * item;}));
        System.out.println(functionTest.compute(2,item-> 2 + item));
        System.out.println(functionTest.compute(3,item-> item * item));

        System.out.println(functionTest.convert(5,values -> String.valueOf(values+"hello world")));

        //行为已经定义；
        System.out.println(functionTest.method(2));

        Function<Integer,Integer> function = value -> value * 2;
        System.out.println(functionTest.compute(2,function));
    }


    /**
     * 在使用的时候，定义行为；function.apply(a)不知道行为，只有在执行时才知道，因为执行者传递了具体的行为
     * @param a
     * @param function
     * @return
     */
    public int compute(int a, Function<Integer,Integer> function){
        int result = function.apply(a);
        return result;
    }

    public String  convert(int a, Function<Integer,String> function){
        return function.apply(a);
    }


    public int method(int a){ return 2*a;}
}
