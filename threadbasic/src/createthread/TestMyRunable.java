package createthread;

public class TestMyRunable {
    public static void main(String[] args) {
        //3)创建Runable接口的实现类对象
        MyRunnable runnable = new MyRunnable();

        //4)创建线程对象
        Thread thread = new Thread(runnable);

        //5)开启线程
        thread.start();

        for (int i = 1; i <=10 ; i++) {
            System.out.println("main:"+i);
        }
        //有时调用Thread(Runable)构造方法时，实参也会传递匿名内部类对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=10 ; i++) {
                    System.out.println("sub ______:"+i);
                }
            }
        });

        thread1.start();
    }
}
