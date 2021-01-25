package ReentrantLock类实现同步2;

public class ThreadAA extends Thread {
    private MyService service;

    public ThreadAA(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
