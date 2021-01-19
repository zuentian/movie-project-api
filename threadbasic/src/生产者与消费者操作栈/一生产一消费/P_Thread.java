package 生产者与消费者操作栈.一生产一消费;

public class P_Thread extends Thread {
    private P p;
    public P_Thread(P p){
        super();
        this.p = p ;
    }

    @Override
    public void run() {
        while (true){
            p.pushService();
        }
    }
}
