package 方法join和sleep的区别;

public class ThreadA extends Thread{

    private ThreadB b;
    public ThreadA(ThreadB b){
        super();
        this.b = b;
    }

    @Override
    public void run() {
        try {
            synchronized (b){
                b.start();
                Thread.sleep(6000);//不是锁
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
