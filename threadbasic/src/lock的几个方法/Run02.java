package lock的几个方法;

/**
 * boolean isFair()的作用是判断是不是公平锁
 * 在默认的情况下，ReentrantLock类使用的是非公平锁
 */
public class Run02 {
    public static void main(String[] args) {
        final Service02 service1 = new Service02(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service1.serviceMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        final Service02 service2 = new Service02(false);
        runnable = new Runnable() {
            @Override
            public void run() {
                service2.serviceMethod();
            }
        };
        thread = new Thread(runnable);
        thread.start();

        final Service02 service03 = new Service02(null);
        runnable = new Runnable() {
            @Override
            public void run() {
                service2.serviceMethod();
            }
        };
        thread = new Thread(runnable);
        thread.start();

    }
}
