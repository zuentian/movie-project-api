package JAVA高并发编程详解.chapter24;

import JAVA高并发编程详解.chapter08.BasicThreadPool;
import JAVA高并发编程详解.chapter08.ThreadPool;

public class Operator {

    private final ThreadPool threadPool = new BasicThreadPool(2,6,4,1000);
    public void call(String business){
        //为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        //new Thread(taskHandler).start();
        threadPool.execute(taskHandler);
    }
}
