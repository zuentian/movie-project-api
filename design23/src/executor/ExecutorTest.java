package executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    ExecutorService pool = Executors.newFixedThreadPool(2);

    public void update(String message){
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date()+"调用发券["+message+"]服务，请求");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date()+"调用发券["+message+"]服务，返回结果");
            }
        });
    }
}
