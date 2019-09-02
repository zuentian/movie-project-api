package com.zuer.zuerlvdoubanservice.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.vo.QueryParam;
import com.zuer.zuerlvdoubanservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/User")
public class UserServiceClient  {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUserInfoByUserName",method = RequestMethod.GET)
    public UserInfo queryUserInfoByUserName(@RequestParam("username") String username) {

        User user =new User();
        user.setUsername(username);
        user=userService.selectOne(user);
        UserInfo userInfo=new UserInfo();
        if(user!=null){
            BeanUtils.copyProperties(user,userInfo);
        }
        return  userInfo;
    }


    @RequestMapping(value = "/queryUserByQueryParam",method = RequestMethod.POST)
    public List<User> queryUserByQueryParam(@RequestBody QueryParam queryParam) {

        System.out.println("====================queryParam"+queryParam);

        Class<User> clazz = (Class<User>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if(queryParam.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        System.out.println("example=================="+example);
        Page<Object> result = PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        System.out.println("example+++++++++++++++++++++++++++++");
        List<User> list = userService.selectByExample(example);

        System.out.println("example==================list"+list);
        return list;
    }

}
