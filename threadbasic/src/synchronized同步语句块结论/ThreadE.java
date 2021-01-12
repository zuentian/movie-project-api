package synchronized同步语句块结论;

public class ThreadE extends Thread {
    private Service service;

    private MyObject object;

    public ThreadE(Service service, MyObject object){
        super();
        this.service = service;
        this.object = object;
    }
    @Override
    public void run() {
        super.run();
        service.testMethod1(object);
    }
}
