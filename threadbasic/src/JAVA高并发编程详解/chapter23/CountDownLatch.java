package JAVA高并发编程详解.chapter23;

public class CountDownLatch extends Latch {


    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {

        synchronized (this){
            while (limit > 0){
                this.wait();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this){
            if(limit <= 0){
                throw new IllegalStateException("all of task already arrived");
            }
            limit --;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }
}
