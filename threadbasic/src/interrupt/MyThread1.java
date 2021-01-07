package interrupt;

public class MyThread1 extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println("i="+(i+1));
            }
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先停止，在遇到了sleep! 进入catch!");
            e.printStackTrace();
        }
    }
}
