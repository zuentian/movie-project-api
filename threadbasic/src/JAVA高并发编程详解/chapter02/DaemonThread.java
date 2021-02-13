package JAVA高并发编程详解.chapter02;

/**
 * 线程是否为守护线程，和它的父线程有很大的关系
 * 如果父线程是正常线程，则子线程也是正常线程，反之亦然，如果你想要修改它的特性
 * 则可以借助setDaemon方法。isDaemon方法可以判断线程是不是守护线程
 * 另外需要注意的就是，setDaemon方法只在线程启动之前才能生效，如果一个线程已经死亡
 * 那么设置setDaemon则会跑出IllegalThreadStateException异常
 *
 * 守护线程具备自动结束生命周期的特性，而非守护线程则不具备这个特点
 * 守护线程经常用作与执行一些后台任务，因此有时它叫做后台线程，当你希望关闭某些线程的时候
 * 或者退出JVM进程的时候，一些线程能够自动关闭。
 * 比如垃圾回收的线程
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2_000L);

        System.out.println("Main thread finished lifecycle");
    }
}
