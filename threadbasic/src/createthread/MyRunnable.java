package createthread;

/**
 * 当线程类已经有父类了，就不能用继承Thread类的形式创建线程
 * 可以使用实现Runnable接口的形式
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            System.out.println("sub thread:"+i);
        }
    }
}
