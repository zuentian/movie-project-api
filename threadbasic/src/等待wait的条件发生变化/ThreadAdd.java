package 等待wait的条件发生变化;

public class ThreadAdd extends Thread {
    private Add p;
    public ThreadAdd(Add p){
        super();
        this.p = p;
    }

    @Override
    public void run() {
        p.add();
    }
}
