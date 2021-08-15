package springstudy.ioc.service.impl;

import springstudy.ioc.annoation.IocService;
import springstudy.ioc.service.IOrderService;

@IocService
public class OrderService implements IOrderService {

    @Override
    public String findOrder(String username){
        return "用户"+username+"的订单编号是：1001";
    }
}
