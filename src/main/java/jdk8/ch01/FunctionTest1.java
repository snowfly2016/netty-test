package jdk8.ch01;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest1 {

    /**
     * Function test
     * Function 只能传递一个参数，如果传递两个参数该如何操作呢？
     *
     * BiFunction 就是针对Function的补充
     * <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after)
     * 该函数的参数需要注意，为啥是Function<? super R, ? extends V> after 而不是BiFunction？
     * 是因为Function<? super R, ? extends V>的返回值为一个
     *
     */
    public static void main(String[] args) {
        FunctionTest1 functionTest1 = new FunctionTest1();

        // result =12
        System.out.println(functionTest1.compute(2,value -> value * 3,value -> value * value));

        // reuslt = 36
        System.out.println(functionTest1.compute1(2,value -> value * 3,value -> value * value));

        //BiFunction
        System.out.println(functionTest1.compute2(1,2,(value1,value2)->value1 + value2));
        System.out.println(functionTest1.compute2(5,2,(value1,value2)->value1 - value2));
        System.out.println(functionTest1.compute2(1,2,(value1,value2)->value1 * value2));
        System.out.println(functionTest1.compute2(4,2,(value1,value2)->value1 / value2));


        System.out.println(functionTest1.compute3(2,3,(value1,value2) -> value1 + value2,value -> value * value));

    }

    /**
     *
     * @param a
     * @param function1
     * @param function2
     * @return
     */
    public int compute(int a, Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        return function1.compose(function2).apply(a);
    }

    /**
     *
     * @param a
     * @param b
     * @param function
     * @return
     */
    public int compute2(int a, int b, BiFunction<Integer, Integer,Integer> function){
        return function.apply(a,b);
    }

    public int compute1(int a, Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        return function1.andThen(function2).apply(a);
    }


    /**
     *
     * @param a
     * @param b
     * @param bifunction
     * @param function
     * @return
     */
    public int compute3(int a, int b,BiFunction<Integer, Integer,Integer> bifunction, Function<Integer, Integer> function){
        return bifunction.andThen(function).apply(a,b);
    }
}
