package ThreadLocal的隔离性;

/**
 * ThreadLocal解决的是变量在不同线程间的隔离性，也就是不同线程拥有自己的值
 * 不同线程中的值可以放入ThreadLocal类中进行保存的
 */
public class Run {

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("在Main线程中取值："+Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
