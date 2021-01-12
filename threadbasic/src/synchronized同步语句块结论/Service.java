package synchronized同步语句块结论;

public class Service {

    public void testMethod1(MyObject object){
        synchronized (object){
            try {
                System.out.println("testMethod1__getLock time="
                        +System.currentTimeMillis()+" run ThreadName="
                        +Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("testMethod1 releaseLock time="
                        +System.currentTimeMillis()+" run ThreadName="
                        +Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
