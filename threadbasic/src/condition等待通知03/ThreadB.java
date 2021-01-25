package condition等待通知03;

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
