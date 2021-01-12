package 静态同步syn方法和synclass代码块;

/**
 * 异步的原因是持有不同的锁，一个是对象锁，一个是Class锁
 * Class锁就是synchronized关键词加到static静态方法上
 *
 */
public class Run {
    public static void main(String[] args) {

        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        ThreadC c = new ThreadC(service);
        c.setName("C");
        c.start();
        //注意：同步synchronized(class)代码块的作用其实和synchronized static方法的作用一样
        //只不过synchronized的监视器传入不同的对象，但类是相同的也会异步
        //而synchronized的监视器传入的类相同就会同步
        /*Service service1 = new Service();
        Service service2 = new Service();
        ThreadA a = new ThreadA(service1);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service2);
        b.setName("B");
        b.start();*/
    }
}
