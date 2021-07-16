package subjecmode;

import java.util.concurrent.*;

public class SendNewPersonCouponObserver  implements Observer{

    ExecutorService pool = Executors.newFixedThreadPool(2);
    @Override
    public void update(String message) {
       /* Future<String> future = pool.submit(new Callable<String>(){

            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "调用发券服务，返回结果";
            }
        });
        try {
            System.out.println(future.get(4,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println(111);*/
       pool.execute(new Runnable() {
           @Override
           public void run() {

               try {
                   TimeUnit.SECONDS.sleep(3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(System.currentTimeMillis()+"调用发券服务，返回结果");
           }
       });
    }
}
