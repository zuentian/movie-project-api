package condition等待通知04;

public class ThreadB extends Thread {

    private MyService service;
    public ThreadB(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
