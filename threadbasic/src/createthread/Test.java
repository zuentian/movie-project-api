package createthread;

public class Test {
    public static void main(String[] args) {
        System.out.println("JVM启动main线程，执行main方法");
        //3)创建子线程对象
        MyThread thread = new MyThread();
        //4)启动线程
        thread.start();
        System.out.println("main线程其他的代码…………");
        /*
        调用线程的start()方法来启动线程，启动线程的实质就是请求jvm运行相应的线程
        这个线程具体在什么时候运行由线程调度器决定
        注意：start()方法调用结束并不意味着子线程开始运行
        新开启的线程会执行run()方法
        如果开启了多个线程，start()调用的顺序并不一定是线程启动的顺序
         */
    }
}
