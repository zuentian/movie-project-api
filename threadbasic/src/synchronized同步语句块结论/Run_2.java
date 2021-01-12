package synchronized同步语句块结论;

/**
 * 第二个结论
 * 当其他线程执行x对象中synchronized同步方法时呈同步效果
 */
public class Run_2 {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        MyObject object = new MyObject();
        ThreadC c = new ThreadC(service,object);
        c.setName("c");
        c.start();
        Thread.sleep(100);
        ThreadD d = new ThreadD(object);
        d.setName("d");
        d.start();
    }

}
