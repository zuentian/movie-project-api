package synchronized同步语句块结论;

public class ThreadF extends Thread {
    private MyObject object;

    public ThreadF(MyObject object){
        super();
        this.object = object;
    }
    @Override
    public void run() {
        super.run();
        object.speedPrintStringSynthis();
    }
}
