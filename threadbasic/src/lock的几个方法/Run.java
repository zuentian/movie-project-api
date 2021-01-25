package lock的几个方法;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        service.serviceMethod1();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.servierMethod3();
            }
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
        Thread.sleep(2000);
        System.out.println("有线程数："+service.lock.getQueueLength());

    }

}
