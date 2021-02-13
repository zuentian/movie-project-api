package JAVA高并发编程详解.chapter01.Thread模拟营业大厅叫号机程序;

/**
 * 重写Thread类的run方法和实现Runnable接口的run方法还有一个重要的不同
 * 那就是Thread类的run方法是不能共享的，也就是说A线程不能把B线程的run方法当做自己的执行单元
 * 而使用Runnable接口则很容易就能实现这一点
 * 使用同一个Runnable的实例构造不同而Thread实例
 * static修饰的变量生命周期很长，index这个变量用static修饰不是一种很好的方式
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;//不做static修饰
    private final static int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread()+" 的号码是："+(index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(task,"1号窗口");
        Thread windowThread2 = new Thread(task,"2号窗口");
        Thread windowThread3 = new Thread(task,"3号窗口");
        Thread windowThread4 = new Thread(task,"4号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
