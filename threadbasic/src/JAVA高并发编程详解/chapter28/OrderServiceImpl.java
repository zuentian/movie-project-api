package JAVA高并发编程详解.chapter28;

import JAVA高并发编程详解.chapter19.Future;
import JAVA高并发编程详解.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long,String>newService().submit(input ->
        {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderID->"+orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information";
        },orderId);
    }

    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account+
                    ", orderId "+orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
