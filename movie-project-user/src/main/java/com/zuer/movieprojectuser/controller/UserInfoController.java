package com.zuer.movieprojectuser.controller;


import com.zuer.movieprojectcommon.entity.User;
import com.zuer.movieprojectuser.feignConfig.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/UserInfoController")
@RestController
public class UserInfoController {


    @Autowired
    UserFeignClient userFeignClient;


    @RequestMapping(value = "/search")
    public Map<String,Object> search(@RequestBody Map<String,Object> param) throws Exception{

        try {
            String nameOrMobile=param.get("nameOrMobile")==null?null:(String)param.get("nameOrMobile");
            int pageNum = (Integer)param.get("pageNum");
            int pageSize = (Integer)param.get("pageSize");
            String status = param.get("status")==null?null:(String)param.get("status");

            int start=(pageNum-1)*pageSize+1;
            int end=pageNum*pageSize;

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("nameOrMobile",nameOrMobile);
            map.put("start",start);
            map.put("end",end);
            map.put("status",status);
            List<User> userList=userFeignClient.queryUser(map);
            if(userList==null){
                throw new Exception("查无数据");
            }
            Map<String,Object> resultMap =new HashMap<String,Object>();
            resultMap.put("list",userList);
            resultMap.put("count",userList.size());
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage()) ;
        }
    }



}
