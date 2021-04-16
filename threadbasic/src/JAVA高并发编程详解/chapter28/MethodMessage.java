package JAVA高并发编程详解.chapter28;

import java.util.Map;

public abstract class MethodMessage {

    protected final Map<String,Object> params;

    protected final OrderService orderService;

    protected MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
