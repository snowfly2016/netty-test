package jdk8.ch01;

import java.util.function.Predicate;

public class PredicateTest {

    /**
     * Predicate
     *
     * java.util.function
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        Predicate<String> predicate = p->p.length() >5;

        System.out.println(predicate.test("hello"));

        System.out.println(predicate.test("hello1"));


    }
}
