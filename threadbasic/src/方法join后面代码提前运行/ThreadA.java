package 方法join后面代码提前运行;

public class ThreadA extends Thread {
    private ThreadB b;
    public ThreadA(ThreadB b){
        super();
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (b){
            try {
                System.out.println("begin A ThreadName="+ Thread.currentThread().getName()+"" +
                        System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end A ThreadName="+ Thread.currentThread().getName()+"" +
                        System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
