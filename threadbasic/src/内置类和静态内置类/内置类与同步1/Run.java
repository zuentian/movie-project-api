package 内置类和静态内置类.内置类与同步1;

public class Run {
    /**
     * 内置类中有两个同步方法，但使用的却是不同的锁，打印的结果是异步的
     * @param args
     */
    public static void main(String[] args) {
        final OutClass.Inner inner = new OutClass.Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                inner.method1();
            }
        },"A");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                inner.method2();
            }
        },"B");
        t1.start();
        t2.start();
    }
}
