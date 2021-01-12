package synchronized同步语句块结论;

/**
 * 第三个结论
 * 当其他线程执行x对象方法里面的synchronized(this)代码块时呈同步效果
 */
public class Run_3 {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        MyObject object = new MyObject();
        ThreadE e = new ThreadE(service,object);
        e.setName("e");
        e.start();
        Thread.sleep(100);
        ThreadF f = new ThreadF(object);
        f.setName("f");
        f.start();
    }

}
