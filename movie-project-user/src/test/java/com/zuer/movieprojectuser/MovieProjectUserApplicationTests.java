package com.zuer.movieprojectuser;

import com.zuer.movieprojectuser.entity.User;
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


    @Test
    public void test1(){
        Map<String ,Object> map=new HashMap<>();
        map.put("userCode","");
        List<User> userList=userService.queryUser(map);
        System.out.println(userList);
    }
}
