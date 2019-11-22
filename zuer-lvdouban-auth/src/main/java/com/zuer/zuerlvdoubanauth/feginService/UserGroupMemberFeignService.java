package com.zuer.zuerlvdoubanauth.feginService;

import com.zuer.zuerlvdoubancommon.entity.UserGroupMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("zuer-lvdouban-service")
public interface UserGroupMemberFeignService {
    @RequestMapping(value = "/UserGroupMember/deleteUserGroupMemberByGroupId",method = RequestMethod.GET)
    void deleteUserGroupMemberByGroupId(@RequestParam("groupId") String groupId);

    @RequestMapping(value = "/UserGroupMember/insertUserGroupMember",method = RequestMethod.POST)
    void insertUserGroupMember(UserGroupMember userGroupMember);
}
