package com.zuer.zuerlvdoubanauth.feginService;

import com.zuer.zuerlvdoubancommon.entity.ElementGroup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("zuer-lvdouban-service")
public interface ElementGroupFeignService {


    @RequestMapping(value = "/ElementGroup/insertElementGroup",method = RequestMethod.POST)
    int insertElementGroup(ElementGroup elementGroup);

    @RequestMapping(value = "/ElementGroup/deleteElementGroupByGroupIdAndElementId",method = RequestMethod.POST)
    int deleteElementGroupByGroupIdAndElementId(ElementGroup elementGroup);

    @RequestMapping(value = "/ElementGroup/queryElementGroupByGroupId",method = RequestMethod.GET)
    List<ElementGroup> queryElementGroupByGroupId(@RequestParam("groupId") String groupId);


    @RequestMapping(value = "/ElementGroup/deleteElementGroupByGroupId",method = RequestMethod.GET)
    void deleteElementGroupByGroupId(@RequestParam("groupId") String groupId);

}
