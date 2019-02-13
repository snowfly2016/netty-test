package jdk8.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test1 {
    /**
     * Model1 外部迭代
     * Model2...内部迭代
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        //Model1
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        System.out.println("----------------");
        //Model2
        for (Integer i:list) {
            System.out.println(i);
        }

        System.out.println("----------------");
        //Model3
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });


        //Model4
        System.out.println("#############---------");
        //类型推断
        list.forEach(integer -> {
            System.out.println(integer);
        });


        //Model5
        System.out.println("&&&&&&&&&&&&&&&&&&&---------");
        // 方法引用 method reference
        list.forEach(System.out::println);
    }
}
