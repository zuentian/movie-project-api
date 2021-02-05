package 线程组异常处理;

/**
 * 使用java.lang.ThreadGroup线程组，
 * 并重写uncaughtException方法处理组内线程中断行为时，
 * 每个线程对象中的run()方法内部不要有异常catch语句
 * 如果有catch语句，则public void uncaughtException方法不执行
 */
public class MyThreadGroup extends ThreadGroup{
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t,e);
        this.interrupt();
    }
}
