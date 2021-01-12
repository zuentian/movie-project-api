package synchronized同步语句块结论;

public class ThreadD extends Thread {
    private MyObject object;

    public ThreadD(MyObject object){
        super();
        this.object = object;
    }
    @Override
    public void run() {
        super.run();
        object.speedPrintString();
    }
}
