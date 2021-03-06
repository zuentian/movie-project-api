package JAVA高并发编程详解.chapter19;

import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

        FutureService<String,Integer> service = FutureService.newService();

        Future<Integer> future = service.submit(input ->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        },"Hello");
        //get方法使当前线程进入阻塞，最终会返回计算的结果
        System.out.println(future.get());
    }
}
