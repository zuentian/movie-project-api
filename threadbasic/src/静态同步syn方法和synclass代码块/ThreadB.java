package 静态同步syn方法和synclass代码块;

public class ThreadB extends Thread {
    private Service service;
    public ThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.printB();
    }
}
