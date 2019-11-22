package com.zuer.zuerlvdoubanauth.feginService;

import com.zuer.zuerlvdoubancommon.entity.Element;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface ElementFeignService {

    @RequestMapping(value = "/Element/insertElement",method = RequestMethod.POST)
    int insertElement(Element element);

    @RequestMapping(value = "/Element/queryPageElement",method = RequestMethod.POST)
    Map<String, Object> queryElementParam(@RequestParam("pageSize") String pageSize,
                                          @RequestParam("page") String page,
                                          @RequestBody Map<String, Object> map);

    @RequestMapping(value = "/Element/queryElementById",method = RequestMethod.GET)
    Element queryElementById(@RequestParam("id") String id);


    @RequestMapping(value = "/Element/updateElementById",method = RequestMethod.POST)
    int updateElementById(Element element);

    @RequestMapping(value = "/Element/deleteElementById",method = RequestMethod.GET)
    int deleteElementById(@RequestParam("id") String id);

    @RequestMapping(value = "/Element/queryElementByMenuId",method = RequestMethod.GET)
    List<Element> queryElementByMenuId(@RequestParam("menuId") String menuId);


    @RequestMapping(value = "/Element/getUserAuthorityElementByUserId",method = RequestMethod.GET)
    List<Element> getUserAuthorityElementByUserId(@RequestParam("userId")String userId);

    //当不开启权限控制，查询所有的element功能
    @RequestMapping(value = "/Element/getUserElementAllByUserId",method = RequestMethod.GET)
    List<Element> getUserElementAllByUserId(@RequestParam("userId")String userId);
}
