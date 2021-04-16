package springstudy.ioc.service.impl;

import springstudy.ioc.annoation.IocResource;
import springstudy.ioc.annoation.IocService;
import springstudy.ioc.service.IOrderService;
import springstudy.ioc.service.IUserService;

@IocService(name="userbiz")
public class UserService implements IUserService {

    @IocResource
    private IOrderService orderService;

    @Override
    public String findOrder(String username) {
        return orderService.findOrder(username);
    }
}
