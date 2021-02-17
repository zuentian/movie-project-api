package lock的几个方法;

public class AwaitUninterruptiblyRun {

    public static void main(String[] args) {
        try {
            AwaitUninterruptiblyService service = new AwaitUninterruptiblyService();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    service.testMethod();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
