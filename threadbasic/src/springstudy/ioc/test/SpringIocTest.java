package springstudy.ioc.test;

import springstudy.ioc.annoation.IocResource;
import springstudy.ioc.context.SpringContext;
import springstudy.ioc.service.IUserService;

public class SpringIocTest {

    @IocResource
    IUserService userService;

    public static void main(String[] args) throws Exception {


        String path = "springstudy.ioc.service.impl";
        SpringContext context = new SpringContext(path);

        //IUserService userService = (IUserService)context.getBean("userbiz");

        SpringIocTest t = new SpringIocTest();
        t.s();
    }

    public void s(){

        System.out.println(userService.findOrder("1y1"));
    }
}
