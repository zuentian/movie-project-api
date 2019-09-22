package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "zuer-lvdouban-service")
public interface UserFeignService {
    @RequestMapping(value = "/User/queryUserByQueryParam",method = RequestMethod.POST)
    Map<String,Object>  queryUserByQueryParam( @RequestBody Map<String, Object> map,
                                               @RequestParam("pageSize") String pageSize,
                                               @RequestParam("pageIndex") String pageIndex) ;

    @RequestMapping(value = "/User/queryUserInfoByUserName",method = RequestMethod.GET)
    UserInfo queryUserInfoByUserName(@RequestParam("username") String username) ;


    @RequestMapping(value = "/User/insertUser",method = RequestMethod.POST)
    int insertUser (User user)throws Exception;

    @RequestMapping(value = "/User/queryUserById",method = RequestMethod.GET)
    User queryUserById(@RequestParam("id") String id);

    @RequestMapping(value = "/User/updateUserById",method = RequestMethod.POST)
    int updateUserById(User user)throws Exception;

    @RequestMapping(value = "/User/deleteUserById",method = RequestMethod.GET)
    int deleteUserById(@RequestParam("id") String id);

    @RequestMapping(value = "/User/queryUserLeaderByGroupId",method = RequestMethod.GET)
    List<User> queryUserLeaderByGroupId(@RequestParam("groupId")String groupId);

    @RequestMapping(value = "/User/queryUserLikeUserNames",method = RequestMethod.GET)
    List<User> queryUserLikeUserNames(@RequestParam("name") String name);

    @RequestMapping(value = "/User/queryUserMemberByGroupId",method = RequestMethod.GET)
    List<User> queryUserMemberByGroupId(@RequestParam("groupId") String groupId);
}
