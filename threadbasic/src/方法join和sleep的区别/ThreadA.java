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
                //Thread.sleep(6000);//不是锁
                b.join();
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String newString = new String();
                    Math.random();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
