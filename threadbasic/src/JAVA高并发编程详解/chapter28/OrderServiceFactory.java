package JAVA高并发编程详解.chapter28;

import static java.lang.Thread.currentThread;

public final class OrderServiceFactory {


    private final static ActiveMessageQueue activeMessageQueue
            = new ActiveMessageQueue();

    private OrderServiceFactory(){

    }

    public static OrderService toActiveObject(OrderService orderService){
        return new OrderServiceProxy(orderService,activeMessageQueue);
    }


    public static void main(String[] args) throws InterruptedException {
        OrderService orderService =
                OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello",453453);
        System.out.println("Return immediately");
        currentThread().join();
    }
}
