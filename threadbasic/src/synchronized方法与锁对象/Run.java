package synchronized方法与锁对象;

public class Run {

    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");
        ThreadB b = new ThreadB(object);
        b.setName("B");
        a.start();
        b.start();
    }

    /**
     * 结论
     * 1）A线程先持有object对象的Lock锁，B线程可以异步的方式调用
     * object对象中的非synchronized类型的方法
     * 2）A线程先持有object对象的Lock锁，B如果在这个时候调用object中的
     * synchronized类型的方法则需要等待，也就是同步
     */
}
