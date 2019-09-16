package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.Menu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MenuFeginService {

    @RequestMapping(value = "/Menu/getUserAuthorityMenuByUserId",method = RequestMethod.GET)
    List<Menu> getUserAuthorityMenuByUserId(@RequestParam("id") String id) ;
    @RequestMapping(value = "/Menu/queryMenu",method = RequestMethod.GET)
    List<Menu> queryMenu();

    @RequestMapping(value = "/Menu/queryMenuByTitle",method = RequestMethod.GET)
    List<Menu> queryMenuByTitle(@RequestParam(value="title", required = false) String title);


    @RequestMapping(value = "/Menu/queryMenuById",method = RequestMethod.GET)
    Menu queryMenuById(@RequestParam("id") String id);

    @RequestMapping(value = "/Menu/insertMenu",method = RequestMethod.POST)
    int insertMenu(Menu menu);

    @RequestMapping(value = "/Menu/updateMenuById",method = RequestMethod.POST)
    int updateMenuById(Menu menu);

    @RequestMapping(value = "/Menu/queryMenuByParentIdCount",method = RequestMethod.GET)
    int queryMenuByParentIdCount(@RequestParam("parentId") String parentId);

    @RequestMapping(value = "/Menu/deleteMenuById",method = RequestMethod.GET)
    int deleteMenuById(@RequestParam("id") String id);


    @RequestMapping(value = "/Menu/queryMenuGroupByGroupIdAndGroupType",method = RequestMethod.GET)
    List<Menu> queryMenuGroupByGroupIdAndGroupType(@RequestParam(value="groupId", required = false) String groupId,
                                                   @RequestParam(value="groupType", required = false) String groupType);



}
