package 生产者与消费者操作栈.一生产多消费;

/**
 * 此问题的出现就是因为MyStack.java类中使用了if语句作为条件判断
 * 因为条件发生改变时并没有得到及时的响应，所以多个呈wait状态的线程被唤醒，
 * 继而执行list.remove(O)代码而出现异常
 * 解决这个办法是，将if改成while语句即可
 * 但是改完之后项目没有出现执行异常，却出现了假死情况
 * 解决办法使用notifyAll()唤醒所有的线程
 */
public class Run {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P p = new P(myStack);
        C r1 = new C(myStack);
        C r2 = new C(myStack);
        C r3 = new C(myStack);
        C r4 = new C(myStack);
        C r5 = new C(myStack);
        P_Thread pThread = new P_Thread(p);
        C_Thread rThread1 = new C_Thread(r1);
        C_Thread rThread2 = new C_Thread(r2);
        C_Thread rThread3 = new C_Thread(r3);
        C_Thread rThread4 = new C_Thread(r4);
        C_Thread rThread5 = new C_Thread(r5);

        pThread.start();
        rThread1.start();
        rThread2.start();
        rThread3.start();
        rThread4.start();
        rThread5.start();

    }
}
