package jdk8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest12 {

    /**
     * 以下函数中的写法类似于SQL语句
     * @param args
     */
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",100,20);
        Student student2 = new Student("lisi",90,20);
        Student student3 = new Student("wangwu",90,30);
        Student student4 = new Student("zhangsan",90,20);
        Student student5 = new Student("wangwu",70,30);

        List<Student> studentList = Arrays.asList(student1,student2,student3,student4,student5);

        Map<String,List<Student>> stringListMap =studentList.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(stringListMap);

        System.out.println("----------------------------------------------------------");

        List<Student> studentList1 = Arrays.asList(student1,student2,student3,student4,student5);

        Map<Integer,List<Student>> integerListMap =studentList1.stream().collect(Collectors.groupingBy(Student::getScore));
        System.out.println(integerListMap);

        System.out.println("----------------------------------------------------------");

        List<Student> studentList2 = Arrays.asList(student1,student2,student3,student4,student5);

        Map<String,Long> stringObjectMap =studentList2.stream().collect(Collectors.groupingBy(Student::getName,Collectors.counting()));
        System.out.println(stringObjectMap);


        System.out.println("----------------------------------------------------------");

        List<Student> studentList3 = Arrays.asList(student1,student2,student3,student4,student5);

        Map<String,Double> doubleMap =studentList3.stream().collect(Collectors.groupingBy(Student::getName,Collectors.averagingDouble(Student::getScore)));
        System.out.println(doubleMap);

        System.out.println("------------------分区----------------------------------------");

        List<Student> studentList4 = Arrays.asList(student1,student2,student3,student4,student5);

        Map<Boolean,List<Student>> listMap =studentList4.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 90));
        System.out.println(listMap);

    }
}
