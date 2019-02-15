package jdk8.ch02;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceTest {

    public static void main(String[] args) {
        Student student1 = new Student("zs",10);
        Student student2 = new Student("ww",90);
        Student student3 = new Student("ls",80);
        Student student4 = new Student("zs",50);

        List<Student> students = Arrays.asList(student1,student2,student3,student4);

        //Collections.sort();

        //Lambda表达式排序
        students.sort((s1,s2)->Student.compareStudentByScore(s1,s2));

        students.forEach(System.out::println);
        System.out.println("-------------------------");

        //方法引用 类名::静态方法 等价于上面的Lambda
        students.sort(Student::compareStudentByScore);
        students.forEach(student -> System.out.print(student.getScore()));


        students.sort((s1,s2)->Student.compareStudentByName(s1,s2));
        students.forEach(System.out::println);
        System.out.println("-------------------------");

        students.sort(Student::compareStudentByName);
        students.forEach(student -> System.out.print(student.getName()));

        //引用名(对象名)::实例方法名
        StudentComparator studentComparator = new StudentComparator();
        students.sort((s1,s2)->studentComparator.compareStudentByScore(s1,s2));

        students.sort(studentComparator::compareStudentByScore);

        students.sort((s1,s2)->studentComparator.compareStudentByName(s1,s2));

        students.sort(studentComparator::compareStudentByName);

    }

}
