package 生产者与消费者.多生产与多消费;


/**
 * 生产者
 */
public class P {
    private String lock;
    public P(String lock){
        super();
        this.lock = lock;
    }
    public void setValue(){
        try {
            synchronized (lock){
                if(!ValueObject.value.equals("")){
                    System.out.println("生产者"+Thread.currentThread().getName()+"WAITING了");
                    lock.wait();
                }
                System.out.println("生产者"+Thread.currentThread().getName()+"RUNNABLE了");
                String value = System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println("set的值是"+value);
                ValueObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
