package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.Group;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("zuer-lvdouban-service")
public interface GroupFeignService {

    @RequestMapping(value = "/Group/insertGroup",method = RequestMethod.POST)
    int insertGroup(Group group) throws Exception;

    @RequestMapping(value = "/Group/queryGroupByGroupTypeId",method = RequestMethod.GET)
    List<Group> queryGroupByGroupTypeId(@RequestParam("groupTypeId") String groupTypeId);

    @RequestMapping(value = "/Group/queryGroupById",method = RequestMethod.GET)
    Group queryGroupById(@RequestParam("id") String id);

    @RequestMapping(value = "/Group/updateGroupById",method = RequestMethod.POST)
    int updateGroupById(Group group);

    @RequestMapping(value = "/Group/queryGroupByParentIdCount",method = RequestMethod.GET)
    int queryGroupByParentIdCount(@RequestParam("parentId") String parentId);

    @RequestMapping(value = "/Group/deleteGroupById",method = RequestMethod.GET)
    int deleteGroupById(@RequestParam("id")String id);
}
