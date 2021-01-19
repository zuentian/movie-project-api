package 生产者与消费者操作栈.一生产多消费;

public class C {
    private MyStack myStack;
    public C(MyStack myStack){
        super();
        this.myStack = myStack;
    }
    public void popService(){
        System.out.println("pop="+myStack.pop());
    }
}
