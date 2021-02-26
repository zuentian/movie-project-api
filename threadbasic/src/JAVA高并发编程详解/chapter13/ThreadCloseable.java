package JAVA高并发编程详解.chapter13;

public class ThreadCloseable extends Thread {
    //volatile关键字保证了started线程的可见性
    private volatile boolean started = true;

    @Override
    public void run() {
        while (started){
            //do work
        }
    }

    public void shutdown(){

    }
}
