package jdk8.ch01;

import java.util.function.Supplier;

public class StudentTest {

    public static void main(String[] args) {
        Supplier<Student> studentSupplier = ()-> new Student();

        System.out.println(studentSupplier.get().getName());

        System.out.println("---------------------------------------");

        //构造方法引用 自动寻找不带参数的构造方法
        Supplier<Student> studentSupplier1 = Student::new;
        System.out.println(studentSupplier1.get().getName());


    }
}
