package lock公平锁与非公平锁;

/**
 * 公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的
 * 即先来先得的FIFO先进先出顺序
 * 而非公平锁就是一种获取锁的抢占机制，是随机获得锁的，和公平锁不一样的就是先来的不一定先得到锁
 * 这个方式可能造成某些线程一直拿不到锁
 */
public class RunFair {
    public static void main(String[] args) {
        //final Service service = new Service(false);
        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("+++线程"+Thread.currentThread().getName()+"" +
                        "运行了");
                service.serviceMethod();
            }
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
