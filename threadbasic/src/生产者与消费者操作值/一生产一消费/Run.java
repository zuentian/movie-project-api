package 生产者与消费者操作值.一生产一消费;

public class Run {
    public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadP.start();
        threadC.start();
    }
}
