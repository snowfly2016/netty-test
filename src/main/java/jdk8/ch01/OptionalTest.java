package jdk8.ch01;

import java.util.Optional;

public class OptionalTest {


    /**
     * ofNullable
     * of
     *
     * @param args
     */
    public static void main(String[] args) {

        Optional<String> optional = Optional.of("hello");

        //调用方式，以下写法不推荐
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        //调用方式，以下写法推荐
        optional.ifPresent(item -> System.out.println(item));

        System.out.println("-----------------------");

        System.out.println(optional.orElse("world"));

        System.out.println("-----------------------");

        System.out.println(optional.orElseGet(()->"hello world"));


        Optional<String> optional1 = Optional.ofNullable("hello");
    }


}
