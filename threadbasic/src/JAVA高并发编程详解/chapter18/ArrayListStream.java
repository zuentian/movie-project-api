package JAVA高并发编程详解.chapter18;

import java.util.Arrays;
import java.util.List;

/**
 * ArrayList生成的stream在多线程的情况下也是线程安全的
 * 具备不可变性结果
 */
public class ArrayListStream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Thread","Concurrency","Scala","Clojure");

        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);

        list.forEach(System.out::println);
    }

}

