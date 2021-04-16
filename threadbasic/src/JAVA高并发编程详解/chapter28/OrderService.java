package JAVA高并发编程详解.chapter28;

import JAVA高并发编程详解.chapter19.Future;

public interface OrderService {

    /**
     * 根据订单编号查询订单明细，有入参也有返回值，但是返回类型必须是Future
     * @param orderId
     * @return
     */
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单，没有返回值
     * @param account
     * @param orderId
     */
    void order(String account,long orderId);

}
