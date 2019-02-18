package jdk8.ch04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {

    /**
     * collect()方法
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",100);
        Student student2 = new Student("lisi",90);
        Student student3 = new Student("wangwu",100);
        Student student4 = new Student("zhaoliu",90);
        Student student5 = new Student("maliu",90);

        List<Student> list = Arrays.asList(student1,student2,student3,student4,student5);

        List<Student> students = list.stream().collect(Collectors.toList());
        students.forEach(System.out::println);

        System.out.println("-------------------------------------------");

        System.out.println("count:"+ list.stream().collect(Collectors.counting()));

        System.out.println("count:"+ list.stream().count());

        System.out.println("-------------------------------------------");


        students.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);


        students.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);

        System.out.println("-------------------------------------------");


        Map<Integer,Map<String,List<Student>>> map =students.stream().collect(Collectors.groupingBy(Student::getScore,Collectors.groupingBy(Student::getName)));

        System.out.println(map);


        Map<Boolean,Map<Boolean,List<Student>>> maps = students.stream().collect(Collectors.groupingBy(s -> s.getScore() > 80,Collectors.groupingBy(s->s.getScore() > 90)));

        System.out.println(maps);


    }
}
