package com.zuer.zuerlvdoubanauth;

import com.zuer.zuerlvdoubanauth.demo.DemoFeignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTests {

    @Autowired
    DemoFeignService demoFeignService;
    /*
    测试多数据源
     */
    @Test
    public void test01() throws Exception {
        System.out.println(demoFeignService.query());
    }

}
