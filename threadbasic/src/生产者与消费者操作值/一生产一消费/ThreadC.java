package 生产者与消费者操作值.一生产一消费;

public class ThreadC extends Thread {
    private C c;
    public ThreadC(C c){
        super();
        this.c = c;
    }

    @Override
    public void run() {
        while (true){
            c.getValue();
        }
    }
}
