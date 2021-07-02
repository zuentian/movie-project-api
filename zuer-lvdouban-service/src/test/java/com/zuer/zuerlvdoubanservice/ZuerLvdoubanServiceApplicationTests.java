package com.zuer.zuerlvdoubanservice;

import com.zuer.zuerlvdoubanservice.redisconfig.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//(classes = ZuerLvdoubanServiceApplicationTests.class)
public class ZuerLvdoubanServiceApplicationTests {

    @Resource
    RedisUtil redisUtil ;
    @Test
    public void contextLoads() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a","阿狸");
        map.put("b","亚索");
        map.put("c","艾希");
        map.put("d","虞姬");
        map.put("e","伽罗");
        //redisUtil.hmset("name",map);

        System.out.println(redisUtil.hget("name","a"));
        System.out.println("===============");
        System.out.println(redisUtil.hmget("name"));
    }

}
