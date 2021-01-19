package 生产者与消费者操作值.一生产一消费;

public class ThreadP extends Thread {
    private P p;
    public ThreadP(P p){
        super();
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.setValue();
        }
    }
}
