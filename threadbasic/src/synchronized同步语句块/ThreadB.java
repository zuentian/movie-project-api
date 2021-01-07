package synchronized同步语句块;

public class ThreadB extends Thread {
    private ObjectService service;
    public ThreadB(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}
