package 静态同步syn方法和synclass代码块;

public class ThreadC extends Thread {
    private Service service;
    public ThreadC(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.printC();
    }
}
