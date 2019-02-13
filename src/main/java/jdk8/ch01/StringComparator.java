package jdk8.ch01;

import java.util.*;

public class StringComparator {

    public static void main(String[] args) {
        List<String>  list = Arrays.asList("zhangsan","wangwu","lisi","zhaosi");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Collections.sort(list,(String o1,String o2)->{
            return o2.compareTo(o1);
        });

        //statemen {return o2.compareTo(o1);}
        Collections.sort(list,( o1, o2)->{
            return o2.compareTo(o1);
        });

        //expressiong o2.compareTo(o1)
        Collections.sort(list,( o1, o2)->o2.compareTo(o1));

        Collections.sort(list,Comparator.reverseOrder());

        System.out.println(list);

    }
}
