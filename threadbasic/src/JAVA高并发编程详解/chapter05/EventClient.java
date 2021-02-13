package JAVA高并发编程详解.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * wait方法是可中断方法，这也就意味着，当前线程一旦调用wait方法进入阻塞状态
 * 其他线程是可以用interrupt方法将其打断
 *
 * 必须在同步方法中使用wait和notify方法，因为执行wait和notify的前提条件是必须持有同步方法
 * 的monitor监视器的所有权
 *
 * wait和sleep
 * 1、wait和sleep方法都可以使线程进入阻塞状态
 * 2、wait和sleep方法都是可中断方法，被中断后都会收到中断异常
 * 3、wait是Object的方法，而sleep是Thread特有的方法
 * 4、wait方法的执行必须在同步方法中进行，而sleep则不需要
 * 5、线程在同步方法中执行sleep方法时，并不会释放monitor的锁，而wait方法则会释放monitor的锁
 * 6、sleep方法短暂休眠之后会主动退出阻塞，
 *    而wait方法在没有指定时间，则需要被其他线程中断后才能退出阻塞
 *
 */
public class EventClient {


    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
           for (;;){
               eventQueue.offer(new EventQueue.Event());
           }
        },"Produce").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }

}
