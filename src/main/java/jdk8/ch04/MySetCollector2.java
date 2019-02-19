package jdk8.ch04;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MySetCollector2<T> implements Collector<T,Set<T>,Map<T,T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked");
        return (set,i)->{
            System.out.println("accumulator "+Thread.currentThread().getName());
            set.add(i);
        };
    }

    /**
     * 并行流才会调用
     * @return
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked");

        return (set1,set2)->{
            set1.addAll(set2);
            return set1;
        };
    }

    /**
     * 该方法在类型一致时，不会被调用，类型不一致才调用
     * @return
     */
    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked");

        return set ->{
            Map<T,T> map  = new HashMap<>();
            set.stream().forEach(item -> map.put(item,item));
            return map;
        };
    }

    /**
     * Characteristics.IDENTITY_FINISH
     *
     *
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked");
        return  Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.CONCURRENT));
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","a","b","c");
        Set<String> set = new HashSet<>();
        set.addAll(list);

        System.out.println(set);

        Map<String,String> map = set.parallelStream().collect(new MySetCollector2<>());

        System.out.println(map);
    }
}
