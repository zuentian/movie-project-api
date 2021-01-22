package 方法join后面代码提前运行;

public class Run1 {
    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();

            ThreadA a = new ThreadA(b);
            b.start();
            a.start();

            b.join();
            System.out.println("main end"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
