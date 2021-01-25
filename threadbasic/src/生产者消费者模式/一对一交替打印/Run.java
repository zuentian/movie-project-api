package 生产者消费者模式.一对一交替打印;

public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA a = new ThreadA(myService);
        a.start();
        ThreadB b = new ThreadB(myService);
        b.start();
    }
}
