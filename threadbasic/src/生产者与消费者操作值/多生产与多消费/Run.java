package 生产者与消费者操作值.多生产与多消费;

/**
 * 程序运行后很可能出现假死状态
 * 在代码中确实已经通过wait/notify进行通信了，但不保证notify唤醒的是异类，也许是同类，
 * 比如生产者唤醒生产者，消费者唤醒消费者这样的情况，如果这样的情况运行的比率积少成多，
 * 就会导致所有的线程都不能继续运行下去
 * 大家都在等待，都成waiting状态，程序最后也就呈假死状态，不能继续运行下去了
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");

        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] cThread = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadP(p);
            pThread[i].setName("生产者"+(i+1));
            cThread[i] = new ThreadC(c);
            cThread[i].setName("消费者"+(i+1));
            pThread[i].start();
            cThread[i].start();
        }
        Thread.sleep(5000);
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].getName()+" "+threads[i].getState());
        }
    }
}
