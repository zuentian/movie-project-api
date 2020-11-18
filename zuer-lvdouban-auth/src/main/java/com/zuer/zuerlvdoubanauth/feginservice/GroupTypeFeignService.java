package com.zuer.zuerlvdoubanauth.feginservice;

import com.zuer.zuerlvdoubancommon.entity.GroupType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface GroupTypeFeignService {

    @RequestMapping(value = "/GroupType/insertGroupType",method = RequestMethod.POST)
    int insertGroupType(GroupType groupType);

    @RequestMapping(value = "/GroupType/queryGroupTypeByParam",method = RequestMethod.POST)
    Map<String, Object> queryGroupTypeByParam(@RequestParam("pageSize") String pageSize,
                                              @RequestParam("page") String page,
                                              @RequestBody Map<String, Object> map);
    @RequestMapping(value = "/GroupType/queryGroupTypeById",method = RequestMethod.GET)
    GroupType queryGroupTypeById(@RequestParam("id") String id);

    @RequestMapping(value = "/GroupType/updateGroupTypeById",method = RequestMethod.POST)
    int updateGroupTypeById(GroupType groupType);
    @RequestMapping(value = "/GroupType/deleteGroupTypeById",method = RequestMethod.GET)
    int deleteGroupTypeById(@RequestParam("id")  String id);

    @RequestMapping(value = "/GroupType/queryGroupType",method = RequestMethod.POST)
    List<GroupType> queryGroupType();
}
