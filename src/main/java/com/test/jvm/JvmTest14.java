package com.test.jvm;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.net.URL;
import java.util.Enumeration;

public class JvmTest14 {

    public static void main(String[] args) throws Exception {
        //
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println(classLoader);

        String resourceName="com/test/jvm/JvmTest14.class";

        Enumeration<URL> urlEnumeration = classLoader.getResources(resourceName);

        while (urlEnumeration.hasMoreElements()){
            URL url = urlEnumeration.nextElement();
            System.out.println(url);
        }


        System.out.println("-----------------------------");

        Class<?> classz = JvmTest14.class;
        System.out.println(classz.getClassLoader());

        Class<?> classzs = String.class;
        System.out.println(classzs.getClassLoader());

    }
}
