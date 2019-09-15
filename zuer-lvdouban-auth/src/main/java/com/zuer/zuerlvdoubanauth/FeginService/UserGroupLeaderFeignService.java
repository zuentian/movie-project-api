package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.UserGroupLeader;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("zuer-lvdouban-service")
public interface UserGroupLeaderFeignService {

    @RequestMapping(value = "/UserGroupLeader/deleteUserGroupLeaderByGroupId",method = RequestMethod.GET)
    void deleteUserGroupLeaderByGroupId(@RequestParam("groupId") String groupId);

    @RequestMapping(value = "/UserGroupLeader/insertUserGroupLeader",method = RequestMethod.POST)
    void insertUserGroupLeader(UserGroupLeader userGroupLeader);
}
