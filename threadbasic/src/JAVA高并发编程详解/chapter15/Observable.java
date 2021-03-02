package JAVA高并发编程详解.chapter15;

public interface Observable {

    //任务生命周期的枚举类型
    enum Cycle{
        STARTED,RUNNING,DONE,ERROR
    }
    Cycle getCycle();

    void start();

    void interrupt();
}
