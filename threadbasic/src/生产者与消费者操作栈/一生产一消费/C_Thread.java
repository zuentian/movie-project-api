package 生产者与消费者操作栈.一生产一消费;

public class C_Thread extends Thread{
    private C r;
    public C_Thread(C r){
        super();
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            r.popService();
        }
    }
}
