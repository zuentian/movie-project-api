package JAVA高并发编程详解.chapter08;

/**
 * 创建线程的工厂
 */
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
