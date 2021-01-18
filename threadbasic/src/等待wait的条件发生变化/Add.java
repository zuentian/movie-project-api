package 等待wait的条件发生变化;

public class Add {
    private String lock;
    public Add(String lock){
        super();
        this.lock = lock;
    }

    public void add(){
        synchronized (lock){
            ValueObject.list.add("anyString");
            System.out.println("notifyAll start");
            lock.notifyAll();
            System.out.println("notifyAll end");
        }
    }
}
