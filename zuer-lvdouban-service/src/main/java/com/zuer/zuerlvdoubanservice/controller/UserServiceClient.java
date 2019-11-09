package com.zuer.zuerlvdoubanservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
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
        UserInfo userInfo=null;
        if(user!=null){
            userInfo=new UserInfo();
            BeanUtils.copyProperties(user,userInfo);
        }
        System.out.println("UserInfo-------"+user);
        System.out.println("UserInfo---------"+userInfo);
        return  userInfo;
    }


    @RequestMapping(value = "/queryUserByQueryParam",method = RequestMethod.POST)
    public Map<String,Object> queryUserByQueryParam(@RequestBody Map<String, Object> map,
                                            @RequestParam("pageSize") String pageSize,
                                            @RequestParam("pageIndex") String pageIndex) {
        Example example=new Example(User.class);
        example.setOrderByClause("UPD_TIME DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();

        if(map.get("name")!=null){
            criteria.orLike("name","%"+map.get("name")+"%");
            criteria.orLike("username","%"+map.get("name")+"%");
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, pageIndex);
        List<User> lists = userService.selectByExampleAndRowBounds(example, rowBounds);
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("list",lists);
        int count = userService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;
    }


    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public int insertUser(@RequestBody User user){

        return userService.insertSelective(user);
    }



    @RequestMapping(value = "/queryUserById",method = RequestMethod.GET)
    public User queryUserById(@RequestParam("id") String id){
        return userService.selectByPrimaryKey(id);
    }

   // @HystrixCommand(fallbackMethod="getFallback")
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    public int updateUserById(@RequestBody User user) {
        return userService.updateByPrimaryKeySelective(user);
    }


    @RequestMapping(value = "/deleteUserById",method = RequestMethod.GET)
    public int deleteUserById(@RequestParam("id") String id){
        return userService.deleteByPrimaryKey(id);
    }


    @RequestMapping(value = "/queryUserLeaderByGroupId",method = RequestMethod.GET)
    public List<User> queryUserLeaderByGroupId(@RequestParam("groupId")String groupId){
        return userService.queryUserLeaderByGroupId(groupId);
    }



    @RequestMapping(value = "/queryUserLikeUserNames",method = RequestMethod.GET)
    public List<User> queryUserLikeUserNames(@RequestParam("name") String name){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.orLike("name","%"+name+"%");
            criteria.orLike("username","%"+name+"%");
            criteria.orLike("nameBak","%"+name+"%");
        }
        return userService.selectByExample(example);
    }


    @RequestMapping(value = "/queryUserMemberByGroupId",method = RequestMethod.GET)
    public List<User> queryUserMemberByGroupId(@RequestParam("groupId") String groupId){
        return userService.queryUserMemberByGroupId(groupId);
    }

    @RequestMapping(value = "/getUserAvatarUrl",method = RequestMethod.GET)
    public String getUserAvatarUrl(@RequestParam("id") String id){
        return userService.getUserAvatarUrl(id);
    }


    @RequestMapping(value = "/updateUserAvatarById",method = RequestMethod.POST)
    public void updateUserAvatarById(@RequestBody User user){
        userService.updateByPrimaryKeySelective(user);
    }
}
