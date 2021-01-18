package 生产者与消费者.多生产与多消费;

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
