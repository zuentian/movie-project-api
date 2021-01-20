package synchronized同步语句块结论;

/**
 * 第一个结论
 * 当多个线程同时执行synchronized(x){}同步代码块时呈同步效果
 * 同步的原因是使用了同一个“对象监视器”。
 */
public class Run_1 {
    public static void main(String[] args) {
        Service service = new Service();
        //当多个线程同时执行synchronized(x){}同步代码块时呈同步效果
        /*MyObject object = new MyObject();
        ThreadA a = new ThreadA(service,object);
        a.setName("a");
        a.start();
        方法join和sleep的区别.ThreadB b = new 方法join和sleep的区别.ThreadB(service,object);
        b.setName("b");
        b.start();*/
        //同步的原因是使用了同一个“对象监视器”,如果不使用同一个对象监视器
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();
        ThreadA a1 = new ThreadA(service,object1);
        a1.setName("a1");
        a1.start();
        ThreadA a2 = new ThreadA(service,object2);
        a2.setName("a2");
        a2.start();
    }

}
