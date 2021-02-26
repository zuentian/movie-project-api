package JAVA高并发编程详解.chapter11;

import static java.lang.Thread.currentThread;

/**
 *
 * 线程上下文类加载器
 *
 * 
 */
public class MainThreadClassLoader {


    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());
    }

}
