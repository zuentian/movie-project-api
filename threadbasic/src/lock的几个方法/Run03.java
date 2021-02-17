package lock的几个方法;

public class Run03 {

    public static void main(String[] args) {
        final Service03 service03 = new Service03(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service03.serviceMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
