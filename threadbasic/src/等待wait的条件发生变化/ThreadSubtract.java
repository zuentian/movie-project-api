package 等待wait的条件发生变化;

public class ThreadSubtract extends Thread {
    private Subtract r ;
    public ThreadSubtract(Subtract r){
        super();
        this.r = r ;
    }

    @Override
    public void run() {
        //r.substract();
        r.substractNew();
    }
}
