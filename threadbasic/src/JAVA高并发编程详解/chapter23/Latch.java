package JAVA高并发编程详解.chapter23;

public abstract class Latch {


    protected int limit;

    public Latch(int limit){
        this.limit = limit;
    }
    public abstract void await() throws InterruptedException;

    public abstract void countDown();

    public abstract int getUnarrived();
}
