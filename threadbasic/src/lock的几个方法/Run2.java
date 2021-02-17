package lock的几个方法;

public class Run2 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod1();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.start();
        Thread.sleep(500);
        /*
        hasQueuedThread(Thread thread)的作用是查询指定的线程是否正在等待获取此锁定
         */
        System.out.println(service.lock.hasQueuedThread(threadA));

        System.out.println(service.lock.hasQueuedThread(threadB));
        /*
        hasQueuedThreads()的作用是查询是否有线程正在等待获取此锁定
         */
        System.out.println(service.lock.hasQueuedThreads());
    }
}
