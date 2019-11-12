package com.zuer.zuerlvdoubanauth;


/*
spring事务测试
因为执行数据库的service是在其他服务上面，所以在这里做简单的Transactional是做不到事务管理

*/

import com.zuer.zuerlvdoubanauth.demo.DemoFeignService;
import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTests {


    @Autowired
    DemoFeignService demoFeignService;

    @Test
    public void test01(){
        Demo demo=new Demo();
        demo.setId(UUID.randomUUID().toString());
        demo.setName("测试加事务111");
        demo.setAge("11");
    }
}
