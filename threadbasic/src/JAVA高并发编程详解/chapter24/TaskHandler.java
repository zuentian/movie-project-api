package JAVA高并发编程详解.chapter24;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * TaskHandler用于处理每一个提交的Request请求
 * 由于TaskHandler将被Thread执行，因此需要实现Runnable接口
 */
public class TaskHandler implements Runnable {

    private final  Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle "+request);
        slowly();
        System.out.println("End handle "+request);
    }

    //模拟请求处理比较耗时，使线程进入短暂的休眠阶段
    private void slowly(){
        try {
            TimeUnit.SECONDS.sleep(current().nextInt());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
