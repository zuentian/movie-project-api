package 等待通知机制;

public class Run {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            ThreadA a = new ThreadA(lock);
            a.start();
            Thread.sleep(50);
            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 方法wait()的作用是使当前执行代码的线程进行等待，
 * wait()方法是Object类的方法，该方法用来将当前线程置入“预执行队列”中
 * 并且在wait()所在的代码行出停止执行，直到接到通知或者被中断为止
 * 在调用wait()之前，线程必须获得该对象的对象级别锁，即只能在同步方法或者同步方法块中
 * 调用wait()方法。
 * 在执行wait()方法后，当前线程释放锁。在从wait()返回前，线程与其他线程竞争重新获得锁。
 * 如果调用wait()时没有持有适当的锁，则抛出异常。
 *
 *
 *
 * 方法notify()也要在同步方法或者同步方法块中调用，即在调用前，线程也必须获得该对象的对象级别锁。
 * 如果调用notify()时没有持有适当的锁，也会抛出异常。
 * 该方法用来通知那些可能等待该对象的对象锁的其他线程，如果多个线程在等待，则由线程随机挑选其中一个呈
 * wait()状态的线程，对其发出通知notify，并使它等待获取该对象的对象锁
 * 需要说明的是，在执行notify()方法后，当前线程不会马上释放该对象锁，呈wait状态的线程
 * 也并不能马上获取该对象锁，要等到执行notify()方法的线程将程序执行完，也就是退出
 * synchronized代码块后，当前线程才会释放锁，而呈wait状态所在的线程才可以获取该对象锁。
 * 当第一个获得了该对象锁的wait线程执行完毕以后，它会释放该对象锁，此时如果该对象没有再次使用notify语句
 * 则即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，还会继续
 * 阻塞在wait，直到这个对象发出一个notify或者notifyAll。
 *
 * notifyAll()方法可以使所有正在等待队列中等待同一共享资源的全部线程
 * 从等待状态退出，进入可运行状态，此时，优先级最高的那个线程最先执行，但也有可能随机执行
 */