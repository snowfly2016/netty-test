package jdk8.ch01;

import java.util.function.Supplier;

public class SupplierTest {

    /**
     * Supplier 不接受参数，返回一个结果
     * 可用于工程，
     *
     * @param args
     */
    public static void main(String[] args) {

        Supplier<String> stringSupplier =()->"hello world";

        System.out.println(stringSupplier.get());





    }




}
