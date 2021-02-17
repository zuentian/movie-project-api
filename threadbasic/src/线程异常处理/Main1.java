package 线程异常处理;

public class Main1 {
    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
