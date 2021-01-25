package condition等待通知03;

public class ThreadA extends Thread {

    private MyService service;
    public ThreadA(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
