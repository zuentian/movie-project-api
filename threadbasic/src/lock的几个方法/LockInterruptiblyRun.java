package lock的几个方法;

public class LockInterruptiblyRun {
    public static void main(String[] args) throws InterruptedException {
        final LockInterruptiblyService service = new LockInterruptiblyService();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
        threadB.interrupt();//打标记
        System.out.println("main end!");
    }
}
