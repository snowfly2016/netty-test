package jdk8.ch01;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        BinaryOperatorTest binaryOperatorTest = new BinaryOperatorTest();

        System.out.println(binaryOperatorTest.compute(1,2,(a,b) -> a+b));
        System.out.println(binaryOperatorTest.compute(1,2,(a,b) -> a*b));

        //获取两个参数中较小的那个，
        System.out.println("---------------------------------------------------");
        System.out.println(binaryOperatorTest.getShort("hello","world", Comparator.comparingInt(String::length)));

        System.out.println(binaryOperatorTest.getShort("hello","world",(a,b)->a.charAt(0) - b.charAt(0)));

    }


    public int compute(int a, int b, BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(a,b);
    }

    public String getShort(String a, String b, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(a,b);
    }

    public String getShort1(String a, String b, Comparator<String> comparator){
        return BinaryOperator.maxBy(comparator).apply(a,b);
    }
}
