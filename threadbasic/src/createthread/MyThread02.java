package createthread;

public class MyThread02  extends  Thread{
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("sub thread:"+i);
                int time = (int)Math.random()*1000;
                Thread.sleep(time);//ms
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
