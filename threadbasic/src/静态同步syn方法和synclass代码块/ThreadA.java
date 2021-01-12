package 静态同步syn方法和synclass代码块;

public class ThreadA extends Thread {
    private Service service;
    public ThreadA(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.printA();
    }
}
