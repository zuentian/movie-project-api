package wait方法通知过早;

public class MyRun {

    private String lock = new String("");
    private Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            try {
                synchronized (lock){
                    System.out.println("begin wait");
                    lock.wait();
                    System.out.println("end wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable runnableB = new Runnable() {
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("begin notify");
                lock.notify();
                System.out.println("end notify");
            }
        }
    };

  /*  public static void main(String[] args) {
        MyRun run = new MyRun();
        Thread a = new Thread(run.runnableA);
        a.start();
        Thread b = new Thread(run.runnableB);
        b.start();
    }*/

    /**
     * 过早通知，方法wait永远不会被通知，那就没必要在执行wait()方法
     * @param args
     * @throws InterruptedException
     */
  public static void main(String[] args) throws InterruptedException {
      MyRun run = new MyRun();
      Thread b = new Thread(run.runnableB);
      b.start();
      Thread.sleep(1000);
      Thread a = new Thread(run.runnableA);
      a.start();
  }
}
