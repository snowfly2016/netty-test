package com.test.jvm;

import java.sql.Connection;
import java.sql.DriverManager;

public class JvmTest27 {

    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("jdbc.drivers"));

        /**
         * java.sql.DriverManager.registerDriver(new Driver());
         * DriverManager
         *
         *
         */
        Class.forName("com.mysql.jdbc.Driver");
        /**
         *
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb","username","password");


    }
}
