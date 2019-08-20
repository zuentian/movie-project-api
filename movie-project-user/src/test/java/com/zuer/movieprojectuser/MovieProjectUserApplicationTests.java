package com.zuer.movieprojectuser;

import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import com.zuer.movieprojectuser.entity.User;
import com.zuer.movieprojectuser.feignConfig.CommonFeignClient;
import com.zuer.movieprojectuser.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieProjectUserApplicationTests {



    @Test
    public void contextLoads() {
    }

    /**
     *
     */
    @Resource
    private UserService userService;

    @Autowired
    private CommonFeignClient commonFeignClient;

    @Test
    public void test1(){
        List<Dict> dictValueList=commonFeignClient.queryDict();

    }
}
