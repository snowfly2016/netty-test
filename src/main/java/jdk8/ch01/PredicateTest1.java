package jdk8.ch01;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest1 {

    /**
     * Predicate<T> and(Predicate<? super T> other) 等价于 &&
     * Predicate<T> negate() 等价于 ！
     * Predicate<T> or(Predicate<? super T> other) 等价于 ||
     * <T> Predicate<T> isEqual(Object targetRef)
     * (null == targetRef) ? Objects::isNull : object -> targetRef.equals(object)
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        // 寻找集合中 奇数 偶数
        PredicateTest1 predicateTest1 = new PredicateTest1();

        predicateTest1.conditionFilter(list,item -> item % 2 == 0);

        System.out.println("########################################");

        predicateTest1.conditionFilter(list,item -> item % 2 != 0);

        System.out.println("########################################");
        predicateTest1.conditionFilter(list,item -> item > 5);

        System.out.println("########################################");
        predicateTest1.conditionFilter(list,item -> item < 3);

        System.out.println("########################################");

        //打印所有的元素
        predicateTest1.conditionFilter(list,item -> true);

        System.out.println("########################################+++++");
        //不打印所有的元素
        predicateTest1.conditionFilter(list,item -> false);

        System.out.println("########################################----");

        //大于5且是偶数
        predicateTest1.conditionFilter1(list,item -> item % 2 == 0,item -> item >5 );

        System.out.println("########################################--------");
        predicateTest1.conditionFilter2(list,item -> item % 2 == 0,item -> item >5 );

        System.out.println("########################################-------");
        predicateTest1.conditionFilter3(list,item -> item % 2 == 0,item -> item >5 );

        System.out.println("########################################-------");
        System.out.println(predicateTest1.isEqual("test").test("test"));

        System.out.println(predicateTest1.isEquals(new Date()).test(new Date()));



    }

    /**
     * 提供了一种更高层次的抽象化;使用者传递行为
     * 以前需要完成上面的需求需要定义很多的方法，而有了函数，则只需要定义一个通用的方法即可
     *
     * @param list
     * @param predicate
     * @return
     */
    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for(Integer integer:list){
            if(predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter1(List<Integer> list, Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for(Integer integer:list){
            if(predicate1.and(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for(Integer integer:list){
            if(predicate1.or(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter3(List<Integer> list, Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for(Integer integer:list){
            if(predicate1.or(predicate2).negate().test(integer)){
                System.out.println(integer);
            }
        }
    }

    public Predicate<String> isEqual(Object o){
        return Predicate.isEqual(o);
    }

    public Predicate<Date> isEquals(Object o){
        return Predicate.isEqual(o);
    }

    public void findAllEvent(List<Integer> list){
        for(Integer integer:list){
            if(integer % 2 == 0){
                System.out.println(integer);
            }
        }
    }

}
