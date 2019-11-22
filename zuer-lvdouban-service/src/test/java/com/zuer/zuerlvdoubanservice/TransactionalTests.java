package com.zuer.zuerlvdoubanservice;


/*

测试spring事务
Transaction有传播性，注意事务的作用域

*/

import com.zuer.zuerlvdoubancommon.demo.Demo;
import com.zuer.zuerlvdoubanservice.demo.DemoServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TransactionalTests {


    @Autowired
    DemoServiceClient demoServiceClient;

    @Test
    public void test01(){
        Demo demo=new Demo();
        demo.setId(UUID.randomUUID().toString());
        demo.setName("service事务测试第2轮");
        demo.setAge("11");
        //demoServiceClient.insertDemo(demo);
    }

    /*
    事务Transaction有四个属性：
    1、一致性
        事务前后数据必须保持一致
    2、原子性
        事务要么全部成功，要么全部不成功
    3、隔离性
        一个事务不能被其他事务所干扰，多个并发事务的数据要隔离
    4、持久性
        一个事务一旦提交，它在数据库里的改变就是永久的，接下来即使数据库发生异常也不应该对其有任何影响
     */

}
