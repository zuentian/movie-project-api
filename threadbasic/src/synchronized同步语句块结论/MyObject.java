package synchronized同步语句块结论;

public class MyObject {

    synchronized public void speedPrintString(){

        System.out.println("testMethod1__getLock time="
                +System.currentTimeMillis()+" run ThreadName="
                +Thread.currentThread().getName());
        System.out.println("--------------");
        System.out.println("testMethod1 releaseLock time="
                +System.currentTimeMillis()+" run ThreadName="
                +Thread.currentThread().getName());
    }

    public void speedPrintStringSynthis() {
        synchronized (this){
            System.out.println("testMethod1__getLock time="
                    +System.currentTimeMillis()+" run ThreadName="
                    +Thread.currentThread().getName());
            System.out.println("speedPrintStringSynthis-----------");
            System.out.println("testMethod1 releaseLock time="
                    +System.currentTimeMillis()+" run ThreadName="
                    +Thread.currentThread().getName());
        }
    }
}
