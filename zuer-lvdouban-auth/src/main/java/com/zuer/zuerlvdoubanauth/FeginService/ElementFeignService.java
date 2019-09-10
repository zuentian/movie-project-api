package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.Element;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
