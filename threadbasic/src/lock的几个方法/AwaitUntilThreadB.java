package lock的几个方法;

public class AwaitUntilThreadB extends Thread {
    private AwaitUntilService service;
    public AwaitUntilThreadB(AwaitUntilService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.notifyMethod();
    }
}
