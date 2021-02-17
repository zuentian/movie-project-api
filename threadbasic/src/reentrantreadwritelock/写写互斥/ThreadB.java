package reentrantreadwritelock.写写互斥;


public class ThreadB extends Thread{
    private Service service;
    public ThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}
