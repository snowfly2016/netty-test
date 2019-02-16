package jdk8.ch02;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceTest {

    public String getString(Supplier<String> stringSupplier){
        return stringSupplier.get() + "test";
    }

    public String getStringFunction(String str, Function<String,String> function){
        return function.apply(str);
    }

    public static void main(String[] args) {
        Student student1 = new Student("zs",10);
        Student student2 = new Student("ww",90);
        Student student3 = new Student("ls",80);
        Student student4 = new Student("zs",50);

        List<Student> students = Arrays.asList(student1,student2,student3,student4);

        //Collections.sort();

        System.out.println("---------------------1--------------------------");
        /**
         * 1.方法引用 类名::静态方法
         *
         */
        //Lambda表达式排序
        students.sort((s1,s2)->Student.compareStudentByScore(s1,s2));
        students.forEach(System.out::println);
        System.out.println("-------------------------");

        List<Student> students1 = Arrays.asList(student1,student2,student3,student4);
        //方法引用 类名::静态方法 等价于上面的Lambda
        students1.sort(Student::compareStudentByScore);
        students1.forEach(student -> System.out.print(student.getScore()));

        List<Student> students2 = Arrays.asList(student1,student2,student3,student4);
        students2.sort((s1,s2)->Student.compareStudentByName(s1,s2));
        students2.forEach(System.out::println);
        System.out.println("-------------------------");

        List<Student> students3 = Arrays.asList(student1,student2,student3,student4);
        students3.sort(Student::compareStudentByName);
        students3.forEach(student -> System.out.print(student.getName()));
        System.out.println("-----------------------------2---------------------------");
        /**
         * 2.引用名(对象名)::实例方法名
         *
         */
        List<Student> students4 = Arrays.asList(student1,student2,student3,student4);
        StudentComparator studentComparator = new StudentComparator();
        students4.sort((s1,s2)->studentComparator.compareStudentByScore(s1,s2));

        List<Student> students5 = Arrays.asList(student1,student2,student3,student4);
        students5.sort(studentComparator::compareStudentByScore);
        students5.forEach(student -> System.out.print(student.getScore()));

        List<Student> students6 = Arrays.asList(student1,student2,student3,student4);
        students6.sort((s1,s2)->studentComparator.compareStudentByName(s1,s2));
        students6.forEach(student -> System.out.print(student.getName()));

        List<Student> students7 = Arrays.asList(student1,student2,student3,student4);
        students7.sort(studentComparator::compareStudentByName);
        students7.forEach(student -> System.out.print(student.getName()));

        System.out.println("-----------------------------3----------------------------");
        /**
         * 3.类名::实例方法
         * sort方法所接收的lambda表达式第一个参数去调用compareByName方法，如果是多个参数，其他只能是作为compareByName的参数传递进去了
         * 第一个student调用compareByName，其他student只能作为compareByName的参数传递进去了
         */
        List<Student> students8 = Arrays.asList(student1,student2,student3,student4);
        students8.sort(Student::compareByName);
        students8.forEach(student -> System.out.print(student.getName()+"\n"));

        List<Student> students9 = Arrays.asList(student1,student2,student3,student4);
        students9.sort(Student::compareByScore);
        students9.forEach(student -> System.out.print(student.getScore()+"\n"));

        System.out.println("---------------------------------------------------------");

        List<String> cityList = Arrays.asList("qingdao","chongqin","tianjing","beijing");

        Collections.sort(cityList,(c1,c2)-> c1.compareToIgnoreCase(c2));
        cityList.forEach(c-> System.out.println(c));

        List<String> cityList1 = Arrays.asList("qingdao","chongqin","tianjing","beijing");

        Collections.sort(cityList1,String::compareToIgnoreCase);
        cityList1.forEach(c-> System.out.println(c));

        System.out.println("---------------------------4------------------------------");
        /**
         * 4.构造方法引用: 类名::new
         *
         */
        MethodReferenceTest methodReferenceTest = new MethodReferenceTest();

        System.out.println(methodReferenceTest.getString(String::new));
        System.out.println(methodReferenceTest.getStringFunction("hello",String::new));


    }

}
