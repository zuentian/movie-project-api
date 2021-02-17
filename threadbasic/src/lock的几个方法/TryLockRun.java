package lock的几个方法;

public class TryLockRun {
    public static void main(String[] args) {
        final TryLockService service = new TryLockService();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
       /* Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();*/

        runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"" +
                        "调用waithMethodTimeUnit时间："+System.currentTimeMillis());
                service.waitMethodTimeUnit();
            }
        };

        Thread threadC = new Thread(runnable);
        threadC.setName("C");
        threadC.start();
        Thread threadD = new Thread(runnable);
        threadD.setName("D");
        threadD.start();
    }
}
