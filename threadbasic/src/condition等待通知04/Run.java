package condition等待通知04;

/**
 * notify和notifyAll方法进行通知时，被通知的线程却是由JVM随机选择的
 * Condition可以实现选择性通知
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.signalAll_A();
    }


}
