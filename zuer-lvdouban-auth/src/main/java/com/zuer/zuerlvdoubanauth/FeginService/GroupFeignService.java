package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.Group;
import org.springframework.cloud.netflix.feign.FeignClient;
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
}
