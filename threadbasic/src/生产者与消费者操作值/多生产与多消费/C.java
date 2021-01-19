package 生产者与消费者操作值.多生产与多消费;

/**
 * 消费者
 */
public class C {
    private String lock;
    public C(String lock){
        super();
        this.lock = lock;
    }
    public void getValue(){
        try {
            synchronized (lock){
                if(ValueObject.value.equals("")){
                    System.out.println("消费者"+Thread.currentThread().getName()+"WAITING了");
                    lock.wait();
                }
                System.out.println("消费者"+Thread.currentThread().getName()+"RUNNABLE了");
                System.out.println("get的值是"+ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
