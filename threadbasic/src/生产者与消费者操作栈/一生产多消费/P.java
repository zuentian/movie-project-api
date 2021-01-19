package 生产者与消费者操作栈.一生产多消费;

/**
 * 生产者P
 */
public class P {
    private MyStack myStack;
    public P(MyStack myStack){
        super();
        this.myStack = myStack;
    }
    public void pushService(){
        myStack.push();
    }
}
