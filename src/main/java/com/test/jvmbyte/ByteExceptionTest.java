package com.test.jvmbyte;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * 对于Java类中的每一个实例方法（非static方法），其在编译后所生产的字节码当中，方法参数的数量总是会比源代码中方法参数的数量多一个this；它位于
 * 方法的第一个参数位置，这样，我们就可以在Java的实例方法中使用this来访问当前对象的属性以及其他方法。
 *
 * 这个操作时在编译期间完成的，即由Javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问；接下来在运行期间，由jvm在调用实例
 * 方法时，自动向实例方法传入该this参数，所以，在实例方法的局部变量表中，至少会有一个指向当前对象的局部变量。
 *
 * max_stack表示这个方法运行的任何时刻所能达到的操作数栈的最大深度；
 * max_locals表示方法执行期间创建的局部变量的数目，包含用来表示传入的参数的局部变量；
 *
 * exception_table 存放的是处理异常的信息
 * 每个exception_table表项由start_pc,end_pc,handler_pc,catch_type组成；
 * start_pc和end_pc表示code数组中的从start_pc到end_pc处（包含start_pc，不包含end_pc）的指令抛出的异常会由这个表项来处理；
 * handler_pc表示处理异常的代码的开始处，catch_type表示会被处理的异常类型，它指向常量池中的一个异常类。当catch_type为0时，表示处理所有的异常
 *
 * Java字节码对于异常的处理方式
 * 1.统一采用异常表的方式来处理异常；
 * 2.在jdk1.4.2之前的版本中，并不是使用异常表的方式来对异常进行处理的，而是采用特定的指令方式。
 * 3.当异常处理存在finally语句块时，现代化的jvm采用的处理方式是将finally语句块的字节码拼接到每一个catch块后面，换句话说，程序中存在多少个catch块，就会在每一个catch块后面重复多少个finally语句块的字节码。
 *
 *
 */
public class ByteExceptionTest {


    public void test(){
        try{
            InputStream inputStream = new FileInputStream("xxxx");

            ServerSocket serverSocket = new ServerSocket(9999);

            serverSocket.accept();

        }catch (FileNotFoundException e){

        }catch (IOException e){

        }catch (Exception e){

        }finally {
            System.out.println("finally");
        }
    }
}
