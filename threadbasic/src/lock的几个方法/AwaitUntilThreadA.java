package lock的几个方法;

public class AwaitUntilThreadA extends Thread {
    private AwaitUntilService service;
    public AwaitUntilThreadA(AwaitUntilService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethod();
    }
}
