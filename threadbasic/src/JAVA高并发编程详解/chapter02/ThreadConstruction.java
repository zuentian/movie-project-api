package JAVA高并发编程详解.chapter02;

/**
 * main线程所在的ThreadGroup称之为main
 * 构造一个线程的时候如果没有显式地指定ThreadGroup,那么它将会和父线程同属于一个ThreadGroup
 * 在默认设置中，子线程会和父线程同属于一个Group之外，它还会和父线程拥有同样的优先级
 * 同样的daemon
 */
public class ThreadConstruction {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group,"t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main thread belong group:"+mainThreadGroup.getName());
        System.out.println("t1 and Main thread belong the same group:"+
                (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 and Main thread not belong the same group:"+
                (mainThreadGroup == t2.getThreadGroup()));
        System.out.println("t2 and thread group  belong the same group:"+
                (group == t2.getThreadGroup()));
    }
}
