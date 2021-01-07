package createthread;

/**
 * 多线程运行结果是随机性的
 */
public class Test01 {

    public static void main(String[] args) {
        MyThread02 thread02 = new MyThread02();

        thread02.start();

        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("main--:"+i);
                int time = (int)Math.random()*1000;
                Thread.sleep(time);//ms
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
