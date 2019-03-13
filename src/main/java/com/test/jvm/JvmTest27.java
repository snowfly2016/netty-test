package com.test.jvm;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 父亲委托机制的优点是能够提高软件系统的安全性，因为在此机制下，用户自定义的类加载器不可能加载应该由父类加载器加载的可靠类，
 * 从而防止不可靠甚至恶意的代码代替父类加载器加载可靠的代码。例如，java.lang.Object类总是由根类加载器加载，
 * 其他任何用户自定义的类加载器都不可能加载含有恶意代码的java.lang.Object类
 *
 * 技术学习思维方式：
 * 技术学习过程中需要做好笔记、将存在的疑问及时记录
 * 提升学习的效率，让自己的掌握的技能产生极大的收益
 * 人和人最大的差距就是如何会思考问题？如何将问题进行记录？
 ********重新认识自己的思维方式是否存在问题？*******************
 * 遗忘是常态，但是如何避免遗忘才是关键，或者说在遗忘后能迅速的回忆起来；
 * 记录笔记
 *
 *
 *
 */
public class JvmTest27 {

    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("jdbc.drivers"));

        /**
         * com.mysql.jdbc.Driver static  java.sql.DriverManager.registerDriver(new Driver())
         * java.sql.DriverManager static loadInitialDrivers()
         *
         * DriverManager
         */
        Class.forName("com.mysql.jdbc.Driver");


        /**
         * DriverManager.getConnection()
         *
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb","username","password");


    }
}
