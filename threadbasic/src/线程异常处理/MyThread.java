package 线程异常处理;

public class MyThread extends Thread{

    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }
}
